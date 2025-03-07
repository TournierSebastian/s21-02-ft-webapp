package com.wallex.financial_platform.configs;

import com.wallex.financial_platform.entities.enums.CurrencyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value(value="${api.terceros.currency}")
    private String baseUrl;

    @Bean(name="cotizaciones")
    public WebClient cotizaciones(CurrencyType currency){
        return WebClient.builder()
                .baseUrl(baseUrl+"/estadisticascambiarias/v1.0/cotizaciones/"+currency)
                .build();
    }

}
