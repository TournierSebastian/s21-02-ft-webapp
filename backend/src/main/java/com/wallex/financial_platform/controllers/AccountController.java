package com.wallex.financial_platform.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.services.impl.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
//    private AccountService accountService;
//
//    @GetMapping
//    public List<Account> getAccounts() {
//        return accountService.getAllAccounts();
//    }
}
