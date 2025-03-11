package com.wallex.financial_platform.controllers;

import com.wallex.financial_platform.dtos.requests.ReservationRequestDTO;
import com.wallex.financial_platform.dtos.responses.ReservationResponseDto;
import com.wallex.financial_platform.entities.Reservation;
import com.wallex.financial_platform.services.impl.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> postReservation(@RequestBody @Valid ReservationRequestDTO reservation){
        return ResponseEntity.ok(reservationService.saveReservation(reservation));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllUserReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getAllReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationsById(id));
    }
}
