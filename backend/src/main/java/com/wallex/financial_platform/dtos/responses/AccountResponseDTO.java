package com.wallex.financial_platform.dtos.responses;

import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Map;


public record AccountResponseDTO(
        Long accountId,
        String cbu,
        String alias,
        CurrencyType currency,
        BigDecimal balance,
        BigDecimal reservedBalance
) {}

