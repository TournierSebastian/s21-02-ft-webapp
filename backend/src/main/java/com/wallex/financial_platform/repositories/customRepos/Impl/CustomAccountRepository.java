package com.wallex.financial_platform.repositories.customRepos.Impl;

import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.customRepos.ICustomAccountRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomAccountRepository implements ICustomAccountRepository {
    private final AccountRepository accountRepository;

//    @Override
    public void findAllByUser(User user) {
//        accountRepository.findByUserId(user.getId())
//                .stream().mapToDouble(account -> account.getBalance()).sum();
    }

    @Override
    public void getBalance(User user) {

    }
}
