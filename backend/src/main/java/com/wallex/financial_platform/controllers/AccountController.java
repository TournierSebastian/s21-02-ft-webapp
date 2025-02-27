package com.wallex.financial_platform.controllers;

import java.util.List;
import java.util.Optional;

import com.wallex.financial_platform.dtos.requests.AccountRequestDTO;
import com.wallex.financial_platform.dtos.requests.CheckAccountRequestDto;
import com.wallex.financial_platform.dtos.responses.AccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wallex.financial_platform.services.impl.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

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
        if (!accountData.alias().isBlank()) {
            return ResponseEntity.ok(accountService.getByAlias(accountData.alias()));
        }
        if (!accountData.cbu().isBlank()) {
            return ResponseEntity.ok(accountService.getByCbu(accountData.cbu()));
        } else {
            throw new IllegalArgumentException("Alias or cbu is required");
        }
    }

}
