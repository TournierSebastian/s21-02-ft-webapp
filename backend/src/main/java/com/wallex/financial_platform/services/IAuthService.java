package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.requests.LoginRequestDTO;
import com.wallex.financial_platform.dtos.requests.RegisterUserRequestDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;
import com.wallex.financial_platform.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface IAuthService extends UserDetailsService {
    User register(RegisterUserRequestDTO registerUserRequestDTO);
    String login(LoginRequestDTO loginRequestDTO);

    @Override
    UserDetails loadUserByUsername(String username);
}
