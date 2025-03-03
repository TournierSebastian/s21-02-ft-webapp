package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.MovementResponseDTO;
import com.wallex.financial_platform.dtos.responses.TransactionResponseDTO;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Movement;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.exceptions.AccountNotFoundException;
import com.wallex.financial_platform.exceptions.MovementNotFoundException;
import com.wallex.financial_platform.repositories.MovementRepository;
import com.wallex.financial_platform.services.IMovementService;
import com.wallex.financial_platform.services.utils.UserContextService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MovementService implements IMovementService {
    private MovementRepository movementRepository;
    private UserContextService userContextService;

    @Override
    public MovementResponseDTO save(Transaction transaction) {
        Movement movement = movementRepository.save(mapToEntity(transaction));
        return mapToDto(movement);
    }

    @Override
    public MovementResponseDTO getMovementById(Long movementId) {
        User user = this.userContextService.getAuthenticatedUser();
        List<Movement> movementList = user.getAccounts().stream()
                .map(Account::getMovements).toList()
                .stream()
                .flatMap(List::stream)
                .toList();
        return mapToDto(movementList.stream()
                .filter(mov -> Objects.equals(mov.getMovementId(), movementId))
                .findFirst().orElseThrow(()-> new MovementNotFoundException("Movement not found")));
    }

    @Override
    public List<MovementResponseDTO> getUserAccountMovements(Long accountId) {
        User user = this.userContextService.getAuthenticatedUser();
        Account account = user.getAccounts().stream()
                .filter(acc -> acc.getAccountId() == accountId)
                .toList().stream().findAny()
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));
        return account.getMovements().stream().map(this::mapToDto).toList();
    }

    private Movement mapToEntity(Transaction transaction){
        return Movement.builder()
                .movementId(null)
                .account(transaction.getSourceAccount())
                .transaction(transaction)
                .amount(transaction.getAmount())
                .description(transaction.getReason())
                .build();
    }
    private MovementResponseDTO mapToDto(Movement movement) {
        return new MovementResponseDTO(
                movement.getMovementId(),
                String.format("/api/transactions/%s", movement.getTransaction().getTransactionId()),
                mapToDTO(movement.getAccount()),
                movement.getDescription(),
                movement.getAmount()
        );
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
