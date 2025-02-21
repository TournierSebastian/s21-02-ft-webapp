package com.wallex.financial_platform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.services.impl.TransactionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
