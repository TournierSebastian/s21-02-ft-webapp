package com.wallex.financial_platform.controllers;

import java.util.Date;
import java.util.List;


import com.wallex.financial_platform.dtos.requests.AccountRequestDTO;
import com.wallex.financial_platform.dtos.requests.CheckAccountRequestDto;
import com.wallex.financial_platform.dtos.responses.*;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.services.impl.MovementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wallex.financial_platform.services.impl.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;
    private MovementService movementService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAccounts() {
        return ResponseEntity.ok(accountService.getByUser());
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(
            @RequestBody @Valid AccountRequestDTO account
    ) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PostMapping("/check")
    public ResponseEntity<CheckAccountResponseDTO> createDestinationAccount(@RequestBody @Valid CheckAccountRequestDto accountData) {
        if (accountData.alias().isBlank() && accountData.cbu().isBlank()) {
            throw new IllegalArgumentException("Alias or cbu is required");
        }
        return ResponseEntity.ok(accountService.checkAccount(accountData));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResumeResponseDTO>> getTransactions(
            @PathVariable("id") Long accountId,
            @RequestParam(required = false) @PastOrPresent Date from,
            @RequestParam(required = false) @PastOrPresent Date to,
            @RequestParam(required = false) TransactionStatus status
    ) {
        return ResponseEntity.ok(accountService.getTransactions(accountId));
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationResponseDto>> getReservations(
            @PathVariable("id") Long accountId
            ) {
        return ResponseEntity.ok(accountService.getReservations(accountId));
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<String>> getCurrencies() {
        List<String> currencies = this.accountService.getCurrencies();
        return ResponseEntity.ok(currencies);
    }
}
