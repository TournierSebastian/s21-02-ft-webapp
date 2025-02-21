package com.wallex.financial_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallex.financial_platform.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
