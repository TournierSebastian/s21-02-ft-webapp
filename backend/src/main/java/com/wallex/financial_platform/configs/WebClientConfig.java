package com.wallex.financial_platform.configs;

import com.wallex.financial_platform.entities.enums.CurrencyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value(value="${api.official.bank}")
    private String baseUrl;

    @Bean(name="officialBankApi")
    public WebClient cotizaciones(){
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
