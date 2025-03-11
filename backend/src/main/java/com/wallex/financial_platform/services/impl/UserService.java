package com.wallex.financial_platform.services.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.wallex.financial_platform.dtos.requests.CheckAccountRequestDto;
import com.wallex.financial_platform.dtos.responses.*;
import com.wallex.financial_platform.entities.*;
import com.wallex.financial_platform.exceptions.account.AccountErrorException;
import com.wallex.financial_platform.exceptions.auth.UserNotFoundException;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.services.IUserService;
import com.wallex.financial_platform.services.utils.UserContextService;
import org.springframework.stereotype.Service;

import com.wallex.financial_platform.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;
    private final UserContextService userContextService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con email " + email + " no encontrado"));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return this.userRepository.findById(id).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con id " + id + " no encontrado"));
    }

    @Override
    public UserResponseDTO getUserByDni(String dni) {
        return this.userRepository.findByDni(dni).map(this::convertToDTO).orElseThrow(()->new UserNotFoundException("Usuario  con dni " + dni + " no encontrado"));
    }

    @Override
    public List<UserResponseDTO> getUserOnline() {
        Optional<User> users = this.userRepository.findById(this.userContextService.getAuthenticatedUser().getId());
        if(users.isEmpty()) {
            throw new UserNotFoundException("No hay usuarios registrados");
        }
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CheckAccountResponseDTO> getDestinationAccounts() {
        User user = userContextService.getAuthenticatedUser();
        return user.getDestinationAccounts().stream().map(this::mapToCheckDTO).toList();
    }

    @Override
    public List<CheckAccountResponseDTO> addDestinationAccount(CheckAccountRequestDto account) {
        User user = userContextService.getAuthenticatedUser();
        CheckAccountResponseDTO acc = accountService.checkAccount(account);
        Account accountValidated = accountRepository.findByCbuOrAlias(acc.cbu(),null).orElseThrow();
        List<Account> userDestinations = user.getDestinationAccounts();
        if (userDestinations.contains(accountValidated)) {
            throw new AccountErrorException("La cuenta ya se encuentra en los destinatarios");
        }
        userDestinations.add(accountValidated);
        user.setDestinationAccounts(userDestinations);
        userRepository.save(user);
        return userDestinations.stream().map(this::mapToCheckDTO).toList();
    }

    public List<ActivityResponseDTO> GetUserActivity() {
        User user = userContextService.getAuthenticatedUser();
        List<Transaction> sourceTransactions = new ArrayList<>();
        List<Transaction> destinationTransactions = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();
        user.getAccounts().stream().forEach(acc ->{
            sourceTransactions.addAll(acc.getSourceTransactions());
            destinationTransactions.addAll(acc.getDestinationTransactions());
            reservationList.addAll(acc.getReservations());
        });
        List<ActivityResponseDTO> response =  new ArrayList<>();

        response.addAll(sourceTransactions.stream().map(this::mapToDto).toList());
        response.addAll(destinationTransactions.stream().map(this::mapToDto).toList());
        response.addAll(reservationList.stream().map(this::mapToDto).toList());
        response = response.stream().sorted(Comparator.comparing(ActivityResponseDTO::getCreatedAt)).toList();
        return response;
    }

    private UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getDni(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getActive()
        );
    }

    private CheckAccountResponseDTO mapToCheckDTO(Account account) {
        return new CheckAccountResponseDTO(
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getUser().getFullName(),
                account.getUser().getDni()
        );
    }
    private ActivityResponseDTO mapToDto(Transaction transaction) {
        User user = userContextService.getAuthenticatedUser();
        Optional<Account> isUserSrcAccount = user.getAccounts().stream().filter(acc -> acc == transaction.getSourceAccount()).findAny();
        return ActivityResponseDTO.builder()
                .transactionId(transaction.getTransactionId())
                .sourceAccount(mapToDTO(transaction.getSourceAccount()))
                .destinationAccount(mapToDTO(transaction.getDestinationAccount()))
                .createdAt(transaction.getTransactionDateTime())
                .description(transaction.getReason())
                .type(transaction.getType().name())
                .status(transaction.getStatus().name())
                .currency(transaction.getSourceAccount().getCurrency())
                .amount(isUserSrcAccount.isPresent()
                        ? BigDecimal.ZERO.subtract(transaction.getAmount())
                        : transaction.getAmount()
                )
                .build();
    }
    private ActivityResponseDTO mapToDto(Reservation reservation) {
        return ActivityResponseDTO.builder()
                .reservationId(reservation.getReservationId())
                .sourceAccount(mapToDTO(reservation.getAccount()))
                .createdAt(reservation.getCreationDate())
                .description(reservation.getDescription())
                .type(reservation.getClass().getName())
                .status(reservation.getStatus().name())
                .currency(reservation.getAccount().getCurrency())
                .amount(BigDecimal.ZERO.subtract(reservation.getReservedAmount()))
                .build();

    }

    private CheckAccountResponseDTO mapToDTO(Account account) {
        return new CheckAccountResponseDTO(
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getUser().getFullName(),
                account.getUser().getDni()
        );
    }

}