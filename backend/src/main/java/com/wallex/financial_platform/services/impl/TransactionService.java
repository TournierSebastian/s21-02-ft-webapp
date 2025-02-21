package com.wallex.financial_platform.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}
