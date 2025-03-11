package com.wallex.financial_platform.configs;

import com.wallex.financial_platform.dtos.officialBank.StadisticVariableResponseDto;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.services.utils.OfficialBankConectorHelper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class InvestmentConfig {
    private AccountRepository accountRepository;
    private OfficialBankConectorHelper officialBankConectorHelper;
    private TransactionRepository transactionRepository;

    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES) // cambiar a dias cuando este en produccion
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
        transactionRepository.save(
                Transaction.builder()
                .sourceAccount(srcAccount)
                .destinationAccount(destAccount)
                .amount(revenue)
                .status(TransactionStatus.COMPLETED)
                .type(TransactionType.INVESTMENT)
                .reason("Rendimientos por cuenta remunerada")
                .build()
        );
    }
}
