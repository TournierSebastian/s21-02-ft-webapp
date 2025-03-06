package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Movement;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.MovementRepository;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class UserDestinationAccountDataLoader {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public void load(){
        Map<User, List<Account>> usersMap = generateMapping();

        for(User user : usersMap.keySet()) {
            user.setDestinationAccounts(usersMap.get(user));
            userRepository.save(user);
        }
    }

    private Map<User, List<Account>> generateMapping() {
        List<Transaction> transactionList = transactionRepository.findAll();
        Map<User, List<Account>> usersMap = new HashMap<>();

        transactionList.stream().forEach(tran -> {
            Account sourceAccount =  accountRepository.findById(tran.getSourceAccount().getAccountId()).orElseThrow();
            User srcUser = sourceAccount.getUser();
            Account destAccount = accountRepository.findById(tran.getDestinationAccount().getAccountId()).orElseThrow();
            List<Account> accountList1 = usersMap.get(srcUser) != null
                    ? usersMap.get(srcUser)
                    : new ArrayList<>();
            if (!accountList1.contains(destAccount)) {
                accountList1.add(destAccount);
            }
            usersMap.put(srcUser,accountList1);
        });
        return usersMap;
    }
}
