package com.wallex.financial_platform.configs;

import com.wallex.financial_platform.dtos.officialBank.StadisticVariableResponseDto;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Movement;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.MovementRepository;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.services.impl.AccountService;
import com.wallex.financial_platform.services.impl.MovementService;
import com.wallex.financial_platform.services.utils.OfficialBankConectorHelper;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableScheduling
public class InvestmentConfig {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OfficialBankConectorHelper officialBankConectorHelper;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MovementRepository movementRepository;

    @Scheduled(cron = "0 0/1 * * * ?") //cada minuto Ojo // cambiar a dias cuando este en produccion
    public void applyInvestments(){
        StadisticVariableResponseDto annualNominalRate = this.officialBankConectorHelper.getLastMonetaryStatisticById(29).getResults().getFirst();
        List<Account> accountList = this.accountRepository.findByCurrency(CurrencyType.ARS);

        Account tesoreriaWallex = accountList.stream().filter(acc -> Objects.equals(acc.getUser().getFullName(), "Tesoreria Wallex")).toList().getFirst();

        accountList.stream()
                .filter(acc -> !acc.getUser().isCardProvider() && !Objects.equals(acc, tesoreriaWallex))
                .forEach(acc -> {
                    if (acc.getAvailableBalance().compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal dailyRevenue = this.calculateRevenue(annualNominalRate.getValor(), acc.getAvailableBalance());
                        this.generateAndSaveTransaction(tesoreriaWallex, acc, dailyRevenue);
                    }
        });
    }

    private BigDecimal calculateRevenue(Double rate, BigDecimal balance){
        double dailyEffectiveRate = Math.pow(1+(rate/100)/360,1);
        return balance.multiply(new BigDecimal(dailyEffectiveRate)).subtract(balance);
    }
    private void generateAndSaveTransaction(Account srcAccount, Account destAccount, BigDecimal revenue){
        Transaction transaction = transactionRepository.save(
                Transaction.builder()
                .sourceAccount(srcAccount)
                .destinationAccount(destAccount)
                .amount(revenue)
                .status(TransactionStatus.COMPLETED)
                .type(TransactionType.INVESTMENT)
                .reason("Rendimientos por cuenta remunerada")
                .build()
        );
        this.generateMovement(transaction, destAccount.getUser());
    }
    private void generateMovement(Transaction transaction, User user) {
        movementRepository.save(
                Movement.builder()
                        .transaction(transaction)
                        .user(user)
                        .amount(transaction.getAmount())
                        .description(transaction.getReason())
                        .build()
        );

    }
}
