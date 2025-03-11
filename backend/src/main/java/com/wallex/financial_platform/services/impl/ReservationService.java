package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.dtos.requests.ReservationRequestDTO;
import com.wallex.financial_platform.dtos.responses.ReservationResponseDto;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Reservation;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.ReservationStatus;
import com.wallex.financial_platform.exceptions.account.AccountNotFoundException;
import com.wallex.financial_platform.exceptions.reservations.ReservationNotFoundException;
import com.wallex.financial_platform.exceptions.transaction.InsufficientFundsException;
import com.wallex.financial_platform.repositories.ReservationRepository;
import com.wallex.financial_platform.services.IReservationService;
import com.wallex.financial_platform.services.utils.UserContextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {
    private ReservationRepository reservationRepository;
    private UserContextService userContextService;

    @Override
    public List<ReservationResponseDto> getReservationsByAccountId(Long accountId) {
        User user = userContextService.getAuthenticatedUser();
        Account account = user.getAccounts().stream()
                .filter(acc -> Objects.equals(acc.getAccountId(), accountId))
                .findAny().orElseThrow(()-> new AccountNotFoundException("Account not found"));
        return account.getReservations().stream().map(this::mapToDTO).toList();
    }

    @Override
    public ReservationResponseDto saveReservation(ReservationRequestDTO reservationReq) {
        User user = userContextService.getAuthenticatedUser();
        Account account = user.getAccounts().stream()
                .filter(acc -> Objects.equals(acc.getAccountId(), reservationReq.accountId()))
                .findAny().orElseThrow(()-> new AccountNotFoundException("Account not found"));
        if (account.getAvailableBalance().compareTo(reservationReq.amount()) <= 0) {
            throw new InsufficientFundsException("Insufficient funds to create a reserve");
        }
        return mapToDTO(reservationRepository.save(mapToEntity(reservationReq, account)));
    }

    @Override
    public List<ReservationResponseDto> getAllUserReservations() {
        User user = userContextService.getAuthenticatedUser();
        List<Reservation> reservationList = user.getAccounts().stream()
                .map(Account::getReservations)
                .flatMap(List::stream)
                .toList();
        return reservationList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public ReservationResponseDto getReservationsById(Long id) {
        User user = userContextService.getAuthenticatedUser();
        Reservation reservation = user.getAccounts().stream()
                .map(Account::getReservations)
                .flatMap(List::stream)
                .filter(res -> Objects.equals(res.getReservationId(), id))
                .findAny()
                .orElseThrow(()-> new ReservationNotFoundException("Reservation Not Found"));
        return mapToDTO(reservation);
    }

    private Reservation mapToEntity(ReservationRequestDTO reserve, Account account) {
        return Reservation.builder()
                .reservedAmount(reserve.amount())
                .account(account)
                .status(ReservationStatus.ACTIVE)
                .description(reserve.description())
                .build();
    }
    private ReservationResponseDto mapToDTO(Reservation reserve) {
        return new ReservationResponseDto(
                reserve.getReservationId(),
                reserve.getAccount().getAccountId(),
                reserve.getReservedAmount(),
                reserve.getCreationDate(),
                reserve.getStatus(),
                reserve.getDescription()
        );
    }
}
