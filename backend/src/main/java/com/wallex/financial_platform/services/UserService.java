package com.wallex.financial_platform.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}