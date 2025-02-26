package com.wallex.financial_platform.controller;

import com.wallex.financial_platform.dtos.responses.TransactionResponseDTO;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.services.impl.TransactionService;
import com.wallex.financial_platform.utils.SampleDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TransactionService transactionService;

    SampleDataTest sampleDataTest = new SampleDataTest();

    private Transaction sampleTransaction1;
    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleTransaction1 = sampleDataTest.getTransactionsList().get(0);
        sampleUser = sampleDataTest.getUserList().get(0);

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication auth = Mockito.mock(Authentication.class);
        when(securityContext.getAuthentication())
                .thenReturn(auth);
        when(auth.getPrincipal())
                .thenReturn(sampleUser.getEmail());
        SecurityContextHolder.setContext(securityContext);

    }

    @Test
    @DisplayName("Test find transaction By Id and user")
    void testFindTransactionByIdAndUser() throws Exception {

        TransactionResponseDTO transactionResponse = mapToDTO(sampleTransaction1);

        given(transactionService.getById(any(Long.class)))
                .willReturn(transactionResponse);

        System.out.println(String.format("/api/transactions/%s",transactionResponse.transactionId()));
        this.mockMvc.perform(get(String.format("/api/transactions/%s",transactionResponse.transactionId())).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.transactionId").value(transactionResponse.transactionId()))
                .andExpect(jsonPath("$.date").value(transactionResponse.date().toString()))
                .andExpect(jsonPath("$.status").value(transactionResponse.status().toString()))
                .andExpect(jsonPath("$.amount").value(transactionResponse.amount()))
                .andExpect(jsonPath("$.reason").value(transactionResponse.reason()))
        ;
    }

    private TransactionResponseDTO mapToDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getTransactionId(),
                transaction.getTransactionDateTime(),
                transaction.getType(),
                transaction.getStatus(),
                transaction.getAmount(),
                transaction.getReason()
        );
    }
}
