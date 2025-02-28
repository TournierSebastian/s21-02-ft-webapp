package com.wallex.financial_platform.controllers;


import com.wallex.financial_platform.dtos.responses.MovementResponseDTO;
import com.wallex.financial_platform.services.impl.MovementService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
@AllArgsConstructor
public class MovementController {
    private MovementService movementService;

    @GetMapping("/{id}")
    public ResponseEntity<List<MovementResponseDTO>> getMovementsByAccount(@PathVariable Long id){
        return ResponseEntity.ok(movementService.getMovements(id));
    }
}
