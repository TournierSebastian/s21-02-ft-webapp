package com.wallex.financial_platform.controllers;

import com.wallex.financial_platform.dtos.responses.ActivityResponseDTO;
import com.wallex.financial_platform.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
@AllArgsConstructor
public class MovementController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> getMovementsByAccount(){
        return ResponseEntity.ok(userService.GetUserActivity());
    }
}
