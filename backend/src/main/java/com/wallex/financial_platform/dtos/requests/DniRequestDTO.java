package com.wallex.financial_platform.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DniRequestDTO {
    @NotEmpty
    private String dni;
}
