package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.responses.UserAccountsResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserAccountsResponseDTO getUserByEmail(String email);
    UserAccountsResponseDTO getUserById(Long id);
    UserAccountsResponseDTO getUserByDni(String username);
    List<UserAccountsResponseDTO> getUserOnline();
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}
