package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.MovementRepository;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovementDataLoader {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


//    public void load() {
//        List<Transaction> transactions = transactionRepository.findAll();
//
//        List<Movement> movements = transactions.stream().map(transaction -> {
//            Account account = transaction.getSourceAccount();
//            User user = accountRepository.findById(account.getAccountId()).orElseThrow().getUser();
//            return new Movement(
//                    null,
//                    user,
//                    transaction,
//                    "Movimiento generado para la transacción " + transaction.getTransactionId(),
//                    transaction.getAmount(),
//                    LocalDateTime.now()
//            );
//        }).toList();
//
//       movementRepository.saveAll(movements);
//
//    }
}
