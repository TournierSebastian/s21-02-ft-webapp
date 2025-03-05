package com.wallex.financial_platform.controllers;

import java.util.Date;

import com.wallex.financial_platform.dtos.requests.CardTransactionRequestDTO;
import com.wallex.financial_platform.dtos.requests.TransactionRequestDTO;
import com.wallex.financial_platform.dtos.responses.TransactionResponseDTO;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wallex.financial_platform.services.impl.TransactionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> save(@RequestBody @Valid TransactionRequestDTO transaction) {
        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @PostMapping("/card")
    public ResponseEntity<TransactionResponseDTO> saveCard(@RequestBody @Valid CardTransactionRequestDTO transaction) {
        return ResponseEntity.ok(transactionService.save(transaction));
    }

}
