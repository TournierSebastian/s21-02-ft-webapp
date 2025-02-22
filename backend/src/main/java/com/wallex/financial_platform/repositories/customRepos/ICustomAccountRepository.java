package com.wallex.financial_platform.repositories.customRepos;

import com.wallex.financial_platform.entities.User;

public interface ICustomAccountRepository {
    void getBalance(User user);
}
