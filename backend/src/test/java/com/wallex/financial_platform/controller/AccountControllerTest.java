package com.wallex.financial_platform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.services.impl.AccountService;
import com.wallex.financial_platform.utils.SampleDataTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import static org.mockito.BDDMockito.given;

//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockitoBean
    AccountService accountService;

    SampleDataTest sampleDataTest = new SampleDataTest();

    List<Account> accountsList;

    @BeforeEach
    void setup(){
        accountsList = sampleDataTest.getAccountList();
    }

//    @Test
//    @DisplayName("Test find all accounts By User")
//    void testFindAllAccountsByUser() throws Exception {
//        given(accountService.getAllAccounts()).willReturn(accountsList);
//        this.mockMvc.perform(get("/accounts").accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", Matchers.hasSize(3)));
//    }
}
