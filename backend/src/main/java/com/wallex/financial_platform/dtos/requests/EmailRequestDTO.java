package com.wallex.financial_platform.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailRequestDTO {
    @NotEmpty
    private String email;
}
