package com.wallex.financial_platform.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wallex.financial_platform.dtos.requests.CheckAccountRequestDto;
import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;
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
    public UserResponseDTO getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con email " + email + " no encontrado"));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return this.userRepository.findById(id).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con id " + id + " no encontrado"));
    }

    @Override
    public UserResponseDTO getUserByDni(String dni) {
        return this.userRepository.findByDni(dni).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con dni " + dni + " no encontrado"));
    }

    @Override
    public List<UserResponseDTO> getUserOnline() {
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

    @Override
    public List<CheckAccountResponseDTO> getDestinationAccounts() {
        User user = userContextService.getAuthenticatedUser();
        return user.getDestinationAccounts().stream().map(this::mapToCheckDTO).toList();
    }

    @Override
    public List<CheckAccountResponseDTO> addDestinationAccount(CheckAccountRequestDto account) {

        return null;
    }

    private UserResponseDTO convertToDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getDni(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getActive()
        );
    }

    private CheckAccountResponseDTO mapToCheckDTO(Account account) {
        return new CheckAccountResponseDTO(
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getUser().getFullName(),
                account.getUser().getDni()
        );
    }

}