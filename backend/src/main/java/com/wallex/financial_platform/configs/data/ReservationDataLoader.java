package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Reservation;
import com.wallex.financial_platform.entities.enums.ReservationStatus;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationDataLoader {

    private final ReservationRepository reservationRepository;
    private final AccountRepository accountRepository;

    public void load() {
        // Obtener algunas cuentas para asociar con las reservas
        Account account1 = accountRepository.findById(1L).orElseThrow();
        Account account2 = accountRepository.findById(2L).orElseThrow();
        Account account3 = accountRepository.findById(3L).orElseThrow();

        // Crear las reservas para la cuenta 1
        Reservation reservation1 = new Reservation(
                null,
                account1,
                new BigDecimal("10000.00"),
                LocalDateTime.now(),
                ReservationStatus.ACTIVE,
                "para Abono celular"
        );

        Reservation reservation2 = new Reservation(
                null,
                account1,
                new BigDecimal("70000.00"),
                LocalDateTime.now(),
                ReservationStatus.ACTIVE,
                "para facultad"
        );

        Reservation reservation3 = new Reservation(
               null,
               account3,
                new BigDecimal("30000.00"),
                LocalDateTime.now(),
               ReservationStatus.ACTIVE,
               "para salud"
       );

        reservationRepository.saveAll(List.of(reservation1, reservation2, reservation3));
    }
}
