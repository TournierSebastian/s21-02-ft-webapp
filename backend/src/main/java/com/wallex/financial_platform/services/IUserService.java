package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.responses.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO getUserByEmail(String email);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByDni(String username);
    List<UserResponseDTO> getUserOnline();
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}
