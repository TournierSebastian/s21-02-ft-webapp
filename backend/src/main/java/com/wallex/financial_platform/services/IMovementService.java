package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.responses.MovementResponseDTO;
import com.wallex.financial_platform.entities.Transaction;

import java.util.List;

public interface IMovementService {
    MovementResponseDTO save(Transaction transaction);
    List<MovementResponseDTO> getMovements(Long accountId);
}
