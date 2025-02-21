package com.wallex.financial_platform.service;

import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.services.impl.TransactionService;
import com.wallex.financial_platform.services.impl.UserService;
import com.wallex.financial_platform.utils.enums.TransactionType;

public class AccountService {
    @Mock
    private UserService userService;
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private AccountService accountService;

    private User sampleUser1;
    private User sampleUser2;
    private List<Account> sampleUser1Accounts;
    private List<Account> sampleUser2Accounts;

    @BeforeEach
    void setup(){

        sampleUser1 = User.builder()
            .id(1L)
            .fullName("Gustavo Paz")
            .email("gusti.paz@gmail.com")
            .phone(1185859612)
            .password("abc123")
            .build();
        sampleUser2 = User.builder()
            .id(2L)
            .fullName("Sebastian Turner")
            .email("seba.turner@gmail.com")
            .phone(1185859613)
            .password("abc123")
            .build();
        Account account3 = Account.builder()
            .id(3L)
            .cbu(12345678913L)
            .alias("tabla.soporte.celular")
            .currency(Currency.getInstance("ARS"))
            .user(sampleUser2)
            .build();
        Account accountPesos = Account.builder()
            .id(1L)
            .cbu(12345678912L)
            .alias("lampara.oro.gato")
            .currency(Currency.getInstance("ARS"))
            .user(sampleUser1)
            .build();
        Account accountDolar = Account.builder()
            .id(2L)
            .cbu(12345678932L)
            .alias("lampara.oro.perro")
            .currency(Currency.getInstance("USD"))
            .user(sampleUser1)
            .build();
        Transaction transaction1 = Transaction.builder()
            .id(1L)
            .source(accountPesos)
            .destination(account3)
            .amount(500000.0)
            .type(TransactionType.TRANSFER)
            .reason("compra de cosas muebles")
            .build();
        sampleUser1Accounts = List.of(accountPesos,accountDolar);
        sampleUser2Accounts = List.of(account3);
    }


    @Test
    void findAllAccountsByUser(){

    }

}
