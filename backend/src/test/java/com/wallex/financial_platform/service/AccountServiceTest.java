package com.wallex.financial_platform.service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.enums.TransactionType;
import com.wallex.financial_platform.utils.SampleDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.services.impl.AccountService;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private SampleDataTest sampleDataTest = new SampleDataTest();

    private User sampleUser;
    private List<Account> sampleUserAccounts;


    @BeforeEach
    void setup(){
        sampleUser = sampleDataTest.getUserList().get(1);
        sampleUserAccounts = sampleDataTest.getAccountList().stream()
                .filter(account -> Objects.equals(account.getUser().getId(), sampleUser.getId()))
                .toList();
    }


    @Test
    void findAllAccountsByUser(){
        given(accountRepository.findById(any(Long.class)))
            .willReturn(Optional.ofNullable(sampleUserAccounts.getFirst()));

        Account account = accountService.getAccountById(1L);

        BigDecimal totalRecibidos = account.getDestinationTransactions().stream()
                //.filter(transaction -> transaction.getType().equals(TransactionType.DEPOSIT))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalEnviados = account.getSourceTransactions().stream()
                //.filter(transaction -> transaction.getType().equals(TransactionType.WITHDRAWAL))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        account.setAvailableBalance(totalRecibidos.subtract(totalEnviados));

        System.out.println(account.getAvailableBalance());

        assertThat(account).isEqualTo(sampleUserAccounts.getFirst());
    }

}
