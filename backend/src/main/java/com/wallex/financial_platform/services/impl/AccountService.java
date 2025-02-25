package com.wallex.financial_platform.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wallex.financial_platform.dtos.responses.AccountResponseDTO;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;
import com.wallex.financial_platform.exceptions.AccountNotFoundException;
import com.wallex.financial_platform.repositories.UserRepository;
import com.wallex.financial_platform.services.IAccountService;
import com.wallex.financial_platform.services.utils.UserContextService;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.repositories.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private UserContextService userContextService;

    @SneakyThrows
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return mapToDTO(account);
    }
    @SneakyThrows
    public List<AccountResponseDTO> getByUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && auth.getPrincipal() == "anonymousUser") {
            throw new Exception("User not authenticated");
        }
        User user = this.userContextService.getAuthenticatedUser();

        List<Account> findUserAccounts = accountRepository.findByUserId(user.getId());
        return findUserAccounts.stream().map(account ->
            this.getAccountById(account.getAccountId())
        ).toList();
    }

    @Override
    public AccountResponseDTO getByCbu(String cbu) {
        return mapToDTO(accountRepository.findByCbu(cbu)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"))
        );
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

}
