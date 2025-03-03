package com.wallex.financial_platform.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wallex.financial_platform.dtos.responses.AccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserAccountsResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.exceptions.auth.UserNotFoundException;
import com.wallex.financial_platform.services.IUserService;
import com.wallex.financial_platform.services.utils.UserContextService;
import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserContextService userContextService;

    @Override
    public UserAccountsResponseDTO getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con email " + email + " no encontrado"));
    }

    @Override
    public UserAccountsResponseDTO getUserById(Long id) {
        return this.userRepository.findById(id).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con id " + id + " no encontrado"));
    }

    @Override
    public UserAccountsResponseDTO getUserByDni(String dni) {
        return this.userRepository.findByDni(dni).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con dni " + dni + " no encontrado"));
    }

    @Override
    public List<UserAccountsResponseDTO> getUserOnline() {
        Optional<User> users = this.userRepository.findById(this.userContextService.getAuthenticatedUser().getId());
        if(users.isEmpty()) {
            throw new UserNotFoundException("No hay usuarios registrados");
        }
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDni(String dni) {
        return this.userRepository.existsByDni(dni);
    }

    private UserAccountsResponseDTO convertToDTO(User user) {
        List<AccountResponseDTO> accounts = user.getAccounts() != null
                ? user.getAccounts().stream()
                .map(this::convertToAccountResponseDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return new UserAccountsResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getDni(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getActive(),
                accounts // Agregamos la lista de cuentas al DTO
        );
    }

    private AccountResponseDTO convertToAccountResponseDTO(Account account) {
        return new AccountResponseDTO(
                account.getAccountId(),
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getAvailableBalance(),
                account.getReservedBalance()
        );
    }

}