package com.wallex.financial_platform.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import com.wallex.financial_platform.utils.SampleDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.services.impl.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private SampleDataTest sampleDataTest = new SampleDataTest();

    private User sampleUser1;
    private User sampleUser2;
    private List<Account> sampleUser1Accounts;
    private List<Account> sampleUser2Accounts;
    private List<Transaction> sampleTransacctions;

    @BeforeEach
    void setup(){
        sampleUser1 = sampleDataTest.getUserList().get(0);
        sampleUser2 = sampleDataTest.getUserList().get(1);
        sampleUser1Accounts = sampleDataTest.getAccountList().subList(0, 2);
        sampleUser2Accounts = sampleDataTest.getAccountList().subList(2, 3);
        sampleTransacctions = sampleDataTest.getTransactionsList();

    }

    @Test
    @DisplayName("Test find all transactions")
    void findAllTransaction(){
        given(transactionRepository.findAll())
            .willReturn(sampleTransacctions);

        List<Transaction> transactions = transactionService.getAllTransactions();

        System.out.println(transactions.getFirst().getReason());
        assertThat(transactions).isNotNull();
        assertThat(transactions).hasSize(sampleTransacctions.size());
        assertThat(transactions.getFirst().getReason()).isEqualTo(sampleTransacctions.getFirst().getReason());
    }

}
