package com.wallex.financial_platform.dtos.responses;

import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ActivityResponseDTO {
    private Long transactionId;
    private Long reservationId;
    private CheckAccountResponseDTO sourceAccount;
    private CheckAccountResponseDTO destinationAccount;
    private LocalDateTime createdAt;
    private String description;
    private String type;
    private String status;
    private CurrencyType currency;
    private BigDecimal amount;
}
