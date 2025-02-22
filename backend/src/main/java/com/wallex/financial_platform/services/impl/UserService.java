package com.wallex.financial_platform.services.impl;

import java.util.List;

import com.wallex.financial_platform.dtos.responses.UserResponseDTO;
import com.wallex.financial_platform.services.IUserService;
import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserResponseDTO getUserById(int id) {
        return null;
    }

    @Override
    public UserResponseDTO getUserByDni(String username) {
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}