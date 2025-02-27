package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.requests.LoginRequestDTO;
import com.wallex.financial_platform.dtos.requests.RegisterUserRequestDTO;
import com.wallex.financial_platform.dtos.responses.AuthResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;

public interface IAuthService {
    UserResponseDTO register(RegisterUserRequestDTO registerUserRequestDTO);
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
}
