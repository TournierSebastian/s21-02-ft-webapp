package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.MovementResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Movement;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.exceptions.movement.MovementNotFoundException;
import com.wallex.financial_platform.repositories.MovementRepository;
import com.wallex.financial_platform.services.IMovementService;
import com.wallex.financial_platform.services.utils.UserContextService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public MovementResponseDTO save(Movement movement) {
        return mapToDto(movementRepository.save(movement));
    }

    @Override
    public MovementResponseDTO getMovementById(Long movementId) {
        User user = this.userContextService.getAuthenticatedUser();
        Movement movement = user.getMovements().stream()
                .filter(mov -> Objects.equals(mov.getMovementId(), movementId))
                .findAny().orElseThrow(()-> new MovementNotFoundException("Movement not found"));
        return mapToDto(movement);
    }

    @Override
    public List<MovementResponseDTO> getUserMovements() {
        User user = this.userContextService.getAuthenticatedUser();
        return user.getMovements().stream()
                .map(this::mapToDto)
                .toList();
    }

    private Movement mapToEntity(Transaction transaction){
        return Movement.builder()
                .movementId(null)
                .user(transaction.getSourceAccount().getUser())
                .transaction(transaction)
                .amount(transaction.getAmount())
                .description(transaction.getReason())
                .build();
    }
    private MovementResponseDTO mapToDto(Movement movement) {
        return new MovementResponseDTO(
                movement.getMovementId(),
                movement.getTransaction().getTransactionId(),
                mapToDTO(movement.getTransaction().getSourceAccount()),
                mapToDTO(movement.getTransaction().getDestinationAccount()),
                movement.getMovementDate(),
                movement.getDescription(),
                movement.getTransaction().getType(),
                movement.getTransaction().getStatus(),
                movement.getTransaction().getSourceAccount().getCurrency(),
                movement.getAmount()
        );
    }
    private UserResponseDTO mapToDTO(User user) {
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
