package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.configs.auth.JwtTokenProvider;
import com.wallex.financial_platform.dtos.requests.LoginRequestDTO;
import com.wallex.financial_platform.dtos.requests.RegisterUserRequestDTO;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.UserRepository;
import com.wallex.financial_platform.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User register(RegisterUserRequestDTO registerUserRequestDTO) {
        if (userRepository.existsByEmail(registerUserRequestDTO.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User();
        user.setFullName(registerUserRequestDTO.getFullName());
        user.setDni(registerUserRequestDTO.getDni());
        user.setPhoneNumber(registerUserRequestDTO.getPhone());
        user.setEmail(registerUserRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserRequestDTO.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (userOpt.isEmpty() || !passwordEncoder.matches(loginRequestDTO.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Credenciales inválidas.");
        }
        return jwtTokenProvider.generateToken(loginRequestDTO.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(username);  // Usamos email como username
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
        return userOpt.get(); // Devuelve el usuario si se encuentra, que debe implementar UserDetails
    }
}

