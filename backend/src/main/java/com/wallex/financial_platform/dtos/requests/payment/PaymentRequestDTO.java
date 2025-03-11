package com.wallex.financial_platform.dtos.requests.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        String token,
        Integer issuer_id,
        String payment_method_id,
        @Min(value=1, message="minimum payments is 1 unit") BigDecimal transaction_amount,
        Integer installments,
        String description,
        @JsonProperty(namespace = "payer")
        Payer payer,
        Long cardId
) {
}

