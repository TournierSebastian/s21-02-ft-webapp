package com.wallex.financial_platform.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}
