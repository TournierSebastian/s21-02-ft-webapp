package com.wallex.financial_platform.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wallex.financial_platform.dtos.requests.AccountRequestDTO;
import com.wallex.financial_platform.dtos.responses.AccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.exceptions.AccountErrorException;
import com.wallex.financial_platform.services.IAccountService;
import com.wallex.financial_platform.services.utils.AccountServiceHelper;
import com.wallex.financial_platform.services.utils.UserContextService;
import lombok.SneakyThrows;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.repositories.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private UserContextService userContextService;
    private AccountServiceHelper accountServiceHelper;

    @SneakyThrows
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return mapToDTO(account);
    }
    @SneakyThrows
    public List<AccountResponseDTO> getByUser() {
        User user = this.userContextService.getAuthenticatedUser();

        List<Account> findUserAccounts = accountRepository.findByUserId(user.getId());
        return findUserAccounts.stream().map(account ->
            this.getAccountById(account.getAccountId())
        ).toList();
    }

    @Override
    public CheckAccountResponseDTO getByCbu(String cbu) {
        Optional<Account> account = accountRepository.findByCbu(cbu);
        if (account.isPresent()) {
            return mapToCheckDTO(account.get());
        } else {
            //ES: si no existe, crear una cuenta falsa para simular que la app esta conectada al sistema bancario
            //EN: if not exists, create a fake account to simulate that the app is connected to the banking system
            Account fakeAccount = accountServiceHelper.createFakeAccount(Optional.of(cbu),Optional.empty());
            return mapToCheckDTO(accountRepository.save(fakeAccount));
        }
    }

    @Override
    public CheckAccountResponseDTO getByAlias(String alias) {
        Optional<Account> account = accountRepository.findByAlias(alias);
        if (account.isPresent()) {
            return mapToCheckDTO(account.get());
        } else {
            //ES: si no existe, crear una cuenta falsa para simular que la app esta conectada al sistema bancario
            //EN: if not exists, create a fake account to simulate that the app is connected to the banking system
            Account fakeAccount = accountServiceHelper.createFakeAccount(Optional.empty(),Optional.of(alias));
            return mapToCheckDTO(accountRepository.save(fakeAccount));
        }

    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountReq) {
        User user = this.userContextService.getAuthenticatedUser();
        Faker faker = new Faker();
        List<Account> userAccount = user.getAccounts().stream()
                .filter(account -> account.getCurrency() == accountReq.currency()).toList();
        if (!userAccount.isEmpty()) {
            throw new AccountErrorException("User already has an account with this currency");
        }
        Account newAccount = Account.builder()
                .currency(accountReq.currency())
                .cbu(faker.numerify("CBU000"+"0351"+"Ø"+"000000#######"+"Ø"))
                .alias(faker.animal().name()+"."+faker.construction().materials()+"."+faker.commerce().material())
                .user(user)
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .movements(new ArrayList<>())
                .reservations(new ArrayList<>())
                .build();
        return mapToDTO(accountRepository.save(newAccount));
    }


    private AccountResponseDTO mapToDTO(Account account) {
        return new AccountResponseDTO(
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getAvailableBalance(),
                account.getTransactionTypeBalances()
        );
    }
    private CheckAccountResponseDTO mapToCheckDTO(Account account) {
        return new CheckAccountResponseDTO(
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getUser().getFullName(),
                account.getUser().getDni()
        );
    }
}
