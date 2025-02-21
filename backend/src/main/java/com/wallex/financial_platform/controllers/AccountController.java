package com.wallex.financial_platform.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.services.impl.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class AccountController {
    private AccountService walletService;

    /*@GetMapping
    public List<Account> getAllWallets() {
        return walletService.getAllWallets();
    }*/
}
