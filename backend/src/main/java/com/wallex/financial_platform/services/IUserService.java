package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.requests.CheckAccountRequestDto;
import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;

import java.util.List;
import java.util.Map;

public interface IUserService {
    UserResponseDTO getUserByEmail(String email);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByDni(String username);
    List<UserResponseDTO> getUserOnline();
    List<CheckAccountResponseDTO> getDestinationAccounts();
    List<CheckAccountResponseDTO> addDestinationAccount(CheckAccountRequestDto account);
}
