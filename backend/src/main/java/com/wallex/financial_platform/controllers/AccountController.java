package com.wallex.financial_platform.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.services.impl.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
//    private AccountService accountService;

//    @GetMapping
//    public ResponseEntity<Set<Account>> getAccounts(
//            @RequestParam(name = "user_id", required = false) Optional<Long> userId) {
//        if (userId.isPresent()) {
//            return ResponseEntity.ok(accountService.getAccountsByUserId(userId.get()));
//        } else {
//            throw new IllegalArgumentException("User id is required");
//        }
//    }
}
