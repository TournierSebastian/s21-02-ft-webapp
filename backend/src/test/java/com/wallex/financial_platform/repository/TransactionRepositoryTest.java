package com.wallex.financial_platform.repository;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import com.wallex.financial_platform.utils.SampleDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    private SampleDataTest sampleDataTest = new SampleDataTest();

    private List<Account> sampleCounterPartAccounts = new ArrayList<>();
    private Account sampleAccount1;
    private Account sampleAccount2;
    private User sampleUser;

    @BeforeEach
    void setup(){
        sampleAccount1 = sampleDataTest.getAccountList().get(0);
        List<Account> counterPartAccounts = List.of(
                sampleAccount1.getDestinationTransactions().stream()
                    .map(Transaction::getSourceAccount)
                    .toList(),
                sampleAccount1.getSourceTransactions().stream()
                        .map(Transaction::getDestinationAccount)
                        .toList()
                ).stream()
                .flatMap(List::stream)
                .toList();
        counterPartAccounts.stream().forEach(account -> {
            Optional<User> foundUser = userRepository.findByDni(account.getUser().getDni());
            if (!foundUser.isPresent()) {
                User createUser = account.getUser();
                createUser.setId(null);
                createUser.setAccounts(new ArrayList<>());
                foundUser = Optional.of(userRepository.save(account.getUser()));
            }
            account.setUser(foundUser.get());
            Optional<Account> foundAccount = accountRepository.findByCbu(account.getCbu());
            if (!foundAccount.isPresent()) {
                account.setAccountId(null);
                account.setSourceTransactions(new ArrayList<>());
                account.setDestinationTransactions(new ArrayList<>());
                foundAccount = Optional.of(accountRepository.save(account));
            }
            account.setAccountId(foundAccount.get().getAccountId());
            if (!sampleCounterPartAccounts.contains(account)) {
                sampleCounterPartAccounts.add(account);
            }
        });


        sampleAccount1.setSourceTransactions(new ArrayList<>());
        sampleAccount1.setDestinationTransactions(new ArrayList<>());
        User newUser = sampleAccount1.getUser();
        newUser.setId(null);
        newUser.setAccounts(new ArrayList<>());
        sampleUser = userRepository.save(newUser);

    }

    @Test
    void testSaveAccount() {
        sampleAccount1.setAccountId(null);
        sampleAccount1.setUser(sampleUser);

        Account savedAccount1 = accountRepository.save(sampleAccount1);
        Account findAccount1 = accountRepository.findById(sampleAccount1.getAccountId()).orElse(null);
        assertEquals(savedAccount1, findAccount1);

        List<Transaction> sampleTransactions = List.of(
                sampleDataTest.getAccountList().get(0).getSourceTransactions().stream()
                        .map(transaction -> {
                            transaction.setTransactionId(null);
                            transaction.setSourceAccount(savedAccount1);
                            transaction.setDestinationAccount(
                                    sampleCounterPartAccounts.stream()
                                            .filter(account -> account.getCbu().equals(transaction.getDestinationAccount().getCbu()))
                                            .findFirst().get()
                            );
                            return transaction;
                        }).toList(),
                sampleDataTest.getAccountList().get(0).getDestinationTransactions().stream()
                        .map(transaction -> {
                            transaction.setTransactionId(null);
                            transaction.setDestinationAccount(savedAccount1);
                            transaction.setSourceAccount(
                                    sampleCounterPartAccounts.stream()
                                            .filter(account -> account.getCbu().equals(transaction.getSourceAccount().getCbu()))
                                            .findFirst().get()
                            );
                            return transaction;
                        }).toList()
        ).stream()
        .flatMap(List::stream)
        .toList();

        sampleTransactions.stream().forEach(transaction -> {
            Transaction savedTransaction = transactionRepository.save(transaction);
            Transaction findTransaction = transactionRepository.findById(savedTransaction.getTransactionId()).orElse(null);
            assertEquals(savedTransaction, findTransaction);
        });

        Account updatedAccount = accountRepository.findById(savedAccount1.getAccountId()).orElse(null);

        BigDecimal sampleBalance = calculateBalance(sampleAccount1);
        BigDecimal savedBalance = calculateBalance(updatedAccount);

        assertEquals(sampleBalance, savedBalance);
    }

    private BigDecimal calculateBalance(Account account) {
        BigDecimal totalRecibidos = account.getDestinationTransactions().stream()
                .map(transaction -> transaction.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalEnviados = account.getSourceTransactions().stream()
                .map(transaction -> transaction.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalRecibidos.subtract(totalEnviados);
    }
}
