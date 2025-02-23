package com.wallex.financial_platform.controllers;

import com.wallex.financial_platform.dtos.requests.CardRequestDTO;
import com.wallex.financial_platform.dtos.requests.DniRequestDTO;
import com.wallex.financial_platform.dtos.responses.CardResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;
import com.wallex.financial_platform.services.impl.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getAllCards() {
        List<CardResponseDTO> response = this.cardService.getAllCards();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-dni")
    public ResponseEntity<List<CardResponseDTO>> getCardsByUserDni(@RequestBody DniRequestDTO dni) {
        List<CardResponseDTO> response = cardService.getCardsByUserDni(dni);
        return ResponseEntity.ok(response);
    }


}
