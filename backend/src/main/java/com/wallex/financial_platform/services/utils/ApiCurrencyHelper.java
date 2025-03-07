package com.wallex.financial_platform.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;

@Component
public class ApiCurrencyHelper {

    private final WebClient currencyWebClient;

    @Autowired
    public ApiCurrencyConectorHelper(@Qualifier(value="currencyApi") WebClient currencyWebClient) {
        this.currencyWebClient = currencyWebClient;
    }

    public CurrencyExchangeDto getExchangeRate(Currency currency) {

    }
}
