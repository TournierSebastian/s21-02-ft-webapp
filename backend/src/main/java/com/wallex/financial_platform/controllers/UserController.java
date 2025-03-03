package com.wallex.financial_platform.controllers;

import com.wallex.financial_platform.dtos.requests.DniRequestDTO;
import com.wallex.financial_platform.dtos.requests.EmailRequestDTO;
import com.wallex.financial_platform.dtos.responses.CardResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserAccountsResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.services.impl.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wallex.financial_platform.services.impl.UserService;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor

public class UserController {
    private final UserService userService;
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<UserAccountsResponseDTO>> getAllUsers() {
        List<UserAccountsResponseDTO> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountsResponseDTO> getUserById(@PathVariable Long id) {
        UserAccountsResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-dni")
    public ResponseEntity<UserAccountsResponseDTO> getUserByDni(@RequestBody DniRequestDTO dniRequestDTO) {
        UserAccountsResponseDTO response = userService.getUserByDni(dniRequestDTO.dni());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserAccountsResponseDTO> getUserByEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
        UserAccountsResponseDTO response = userService.getUserByEmail(emailRequestDTO.email());
        return ResponseEntity.ok(response);
    }
}