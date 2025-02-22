package com.wallex.financial_platform.utils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import lombok.Getter;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.utils.enums.AccountStatus;
import com.wallex.financial_platform.utils.enums.TransactionType;

import net.datafaker.Faker;

@Getter
public class SampleDataTest {

    private final Faker faker = new Faker();

    private final List<User> userList;
    private final List<Transaction> transactionsList;
    private final List<Account> accountList;

    public SampleDataTest() {
        userList = new ArrayList<>();
        accountList = new ArrayList<>();
        transactionsList = new ArrayList<>();
        for(int i=0;i < 20;i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            User user = User.builder()
                .id((long) (i + 1))
                .fullName(firstName+" "+lastName)
                .email(firstName+"."+lastName+"@"+faker.company().name().toLowerCase().replaceAll("\\s+","")+".com")
                .phone(faker.phoneNumber().phoneNumberInternational())
                .password(faker.examplify("abc123"))
                .build();
            userList.add(user);
            String accnumber = "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1);
            Account account1 = Account.builder()
                .id(Long.valueOf(accountList.size()+1))
                .cbu(Long.valueOf(faker.numerify("000"+"0351"+"Ø"+accnumber+"Ø")))
                .alias(faker.animal().name()+"."+faker.construction().materials()+"."+faker.commerce().material())
                .currency(Currency.getInstance("ARS"))
                .status(AccountStatus.values()[(int) (Math.random() * AccountStatus.values().length)])
                .user(user)
                .sendedTransactions(new ArrayList<>())
                .receivedTransactions(new ArrayList<>())
                .build();
            accountList.add(account1);
            String accnumber2 = "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1);
            Account account2 = Account.builder()
                .id(Long.valueOf(accountList.size()+1))
                .cbu(Long.valueOf(faker.numerify("000"+"0351"+"Ø"+accnumber2+"Ø")))
                .alias(faker.animal().name()+"."+faker.construction().materials()+"."+faker.commerce().material())
                .currency(Currency.getInstance("USD"))
                .status(AccountStatus.values()[(int) (Math.random() * AccountStatus.values().length)])
                .user(user)
                .sendedTransactions(new ArrayList<>())
                .receivedTransactions(new ArrayList<>())
                .build();
            accountList.add(account2);
            user.setAccount(List.of(account1,account2));
        }
        for (int i = 0; i < 400; i++) {
            Account sourceAccount = accountList.get((int) (Math.random() * accountList.size()));
            Account destAccount = null;
            Currency currency = sourceAccount.getCurrency();
            if (currency.getCurrencyCode() == "ARS") {
                List<Account> ArsAccounts = accountList.stream()
                .filter(acc -> acc.getCurrency().getCurrencyCode() == "ARS" && acc.getId() != sourceAccount.getId())
                .toList();
                destAccount = ArsAccounts.get((int) (Math.random() * ArsAccounts.size()));
            } else {
                List<Account> ArsAccounts = accountList.stream()
                .filter(acc -> acc.getCurrency().getCurrencyCode() == "USD" && acc.getId() != sourceAccount.getId())
                .toList();
                destAccount = ArsAccounts.get((int) (Math.random() * ArsAccounts.size()));
            }
            Transaction transaction = Transaction.builder()
            .id(Long.valueOf(transactionsList.size()+1))
            .source(sourceAccount)
            .destination(destAccount)
            .amount(faker.number().randomDouble(2, 10000, 999999))
            .type(TransactionType.TRANSFER)
            .reason("compra "+faker.appliance().equipment())
            .build();
            transactionsList.add(transaction);

            List<Transaction> sourceAccountSendedtransacions = sourceAccount.getSendedTransactions();
            sourceAccountSendedtransacions.add(transaction);
            sourceAccount.setSendedTransactions(sourceAccountSendedtransacions);
            List<Transaction> destAccountReceivedtransacions = destAccount.getReceivedTransactions();
            destAccountReceivedtransacions.add(transaction);
            destAccount.setReceivedTransactions(destAccountReceivedtransacions);
        }
    }
}
