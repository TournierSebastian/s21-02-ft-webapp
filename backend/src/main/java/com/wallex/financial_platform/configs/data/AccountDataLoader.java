package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountDataLoader {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public void load() {

        List<User> userList = userRepository.findAll();

        Faker faker = new Faker();

        List<Account> accountList = new ArrayList<>();

        accountList.add(
            Account.builder()
                .accountId(null)
                .cbu("CBU000000000000000000000001")
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                .currency(CurrencyType.ARS)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(userList.get(0))
                .reservations(new ArrayList<>())
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .build()
        );

        accountList.add(
            Account.builder()
                .accountId(null)
                .cbu("CBU000000000000000000000002")
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                .currency(CurrencyType.ARS)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(userList.get(1))
                .reservations(new ArrayList<>())
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .build()
        );

        accountList.add(
            Account.builder()
                .accountId(null)
                .cbu("CBU000000000000000000000003")
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                .currency(CurrencyType.ARS)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(userList.get(2))
                .reservations(new ArrayList<>())
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .build()
        );

        accountList.add(
            Account.builder()
                .accountId(null)
                .cbu(faker.numerify("CBU000000000000000000000004")) // CBU único
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                .currency(CurrencyType.ARS)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(userList.get(3))
                .reservations(new ArrayList<>())
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .build()
        );

        accountList.add(
            Account.builder()
                .accountId(null)
                .cbu(faker.numerify("CBU000000000000000000000005")) // CBU único
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                .currency(CurrencyType.ARS)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(userList.get(4))
                .reservations(new ArrayList<>())
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .build()
        );

        userList.stream()
                .filter(p -> "tesoreria@wallex.com".equals(p.getEmail()))
                .findFirst()
                .ifPresentOrElse(
                        user -> {
                            accountList.add(
                                    Account.builder()
                                            .accountId(null)
                                            .cbu("CBU000000000000000000000000")
                                            .alias("tesoreria.wallex.saldo")
                                            .currency(CurrencyType.ARS)
                                            .active(true)
                                            .createdAt(LocalDateTime.now())
                                            .updatedAt(LocalDateTime.now())
                                            .user(user) // Usuario asignado
                                            .reservations(new ArrayList<>())
                                            .sourceTransactions(new ArrayList<>())
                                            .destinationTransactions(new ArrayList<>())
                                            .build()
                            );
                            System.out.println("Usuario encontrado y cuenta creada.");
                        },
                        () -> {
                            System.out.println("No se encontró un usuario con el email especificado.");
                        }
                );

        accountRepository.saveAll(accountList);
    }
}
