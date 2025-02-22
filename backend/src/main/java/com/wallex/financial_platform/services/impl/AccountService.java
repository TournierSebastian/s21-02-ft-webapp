package com.wallex.financial_platform.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.repositories.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

//    public Set<Account> getAccountsByUserId(Long userId) {
//            accountRepository.findByUserId(userId)
//                .stream().sorted();
//    }
}
