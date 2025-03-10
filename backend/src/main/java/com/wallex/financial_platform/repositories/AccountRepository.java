package com.wallex.financial_platform.repositories;

import com.wallex.financial_platform.entities.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wallex.financial_platform.entities.Account;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCbuOrAlias(String cbu, String alias);
    List<Account> findByCurrency(CurrencyType currencyType);
}
