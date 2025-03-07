package com.wallex.financial_platform.services.utils;

import com.wallex.financial_platform.dtos.officialBank.CurrencyExchangeResponseDto;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;

@Component
public class OfficialBankConectorHelper {

    private final WebClient officialBankWebClient;

    @Autowired
    public OfficialBankConectorHelper(@Qualifier(value="officialBankApi") WebClient currencyWebClient) {
        this.officialBankWebClient = currencyWebClient;
    }

    public CurrencyExchangeResponseDto getAllExchangeRates() {
        return this.officialBankWebClient
                .get()
                .uri(uri ->
                        uri.path("/estadisticascambiarias/v1.0/cotizaciones")
                                .build())
                .retrieve()
                .bodyToMono(CurrencyExchangeResponseDto.class)
                .block();
    }

    public CurrencyExchangeResponseDto getExchangeRate(CurrencyType currency) {
        return this.officialBankWebClient
                .get()
                .uri(uri ->
                        uri.path("/estadisticascambiarias/v1.0/cotizaciones/"+currency)
                                .build())
                .retrieve()
                .bodyToMono(CurrencyExchangeResponseDto.class)
                .block();
    }
}
