package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountDataLoader {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public void load() {
        // Obtener algunos usuarios para asociar con las cuentas

        List<User> userList = userRepository.findAll();

        Faker faker = new Faker();

        List<Account> accountList = new ArrayList<>();

        // Crear las cuentas para el usuario 1
       /* accountList.add(
                Account.builder()
                .accountId(null)
                .cbu(faker.numerify("CBU000000"+"0351"+"1"+
                        "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                        "1")
                ) // CBU único
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ","")) // Alias único
                .currency(CurrencyType.ARS) // Moneda
                .active(true)  // Activa
                .createdAt(LocalDateTime.now()) // Fecha de creación
                .updatedAt(LocalDateTime.now()) // Fecha de actualización
                .user(userList.get(0)) // Relación con el usuario 1
                // Puedes omitir o asignar null a los campos opcionales:
                .reservations(new ArrayList<>()) // Reservas (vacío si no tienes datos)
                .sourceTransactions(new ArrayList<>()) // Transacciones origen
                .destinationTransactions(new ArrayList<>()) // Transacciones destino
                .build()
        );*/


        accountList.add(
            Account.builder()
                .accountId(null)
                .cbu(faker.numerify("CBU000000"+"0351"+"1"+
                        "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                        "1")
                ) // CBU único
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
                .cbu(faker.numerify("CBU000000"+"0351"+"Ø"+
                        "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                        "Ø")
                ) // CBU único
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
                .cbu(faker.numerify("CBU000000"+"0351"+"Ø"+
                        "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                        "Ø")
                ) // CBU único
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
                .cbu(faker.numerify("CBU000000"+"0351"+"Ø"+
                        "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                        "Ø")
                ) // CBU único
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

        accountList.add(
            Account.builder()
                .accountId(null)
                .cbu(faker.numerify("CBU000000"+"0351"+"Ø"+
                        "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                        "Ø")
                ) // CBU único
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                .currency(CurrencyType.USD)
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
                        .cbu("CBU007000035119000000099922") // CBU único MELI ARS
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.ARS)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(5))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );
        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu("CBU007000035119000000099921") // CBU único MELI USD
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.USD)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(5))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );
        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu("CBU000000035119000000000021") // CBU único WALLEX tesoreria ARS
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.ARS)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(6))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );
        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu("CBU000000035119900000000021") // CBU único WALLEX tesoreria USD
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.USD)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(6))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );

        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu(faker.numerify("CBU0000012"+"0351"+"Ø"+
                                "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                                "Ø")) // CBU usuario proveedor de tarjetas
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.USD)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(7))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );
        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu(faker.numerify("CBU0000012"+"0351"+"Ø"+
                                "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                                "Ø")) // CBU usuario proveedor de tarjetas
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.ARS)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(7))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );
        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu(faker.numerify("CBU0000012"+"0351"+"Ø"+
                                "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                                "Ø")) // CBU usuario proveedor de tarjetas
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.ARS)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(8))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );
        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu(faker.numerify("CBU0000012"+"0351"+"Ø"+
                                "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                                "Ø")) // CBU usuario proveedor de tarjetas
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.USD)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(8))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );

        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu(faker.numerify("CBU0000012"+"0351"+"Ø"+
                                "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                                "Ø")) // CBU usuario proveedor de tarjetas
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.ARS)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(9))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );
        accountList.add(
                Account.builder()
                        .accountId(null)
                        .cbu(faker.numerify("CBU0000012"+"0351"+"Ø"+
                                "000000000000".substring(0, 12-String.valueOf(accountList.size()+1).length())+(accountList.size()+1)+
                                "Ø")) // CBU usuario proveedor de tarjetas
                        .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ",""))
                        .currency(CurrencyType.USD)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(userList.get(9))
                        .reservations(new ArrayList<>())
                        .sourceTransactions(new ArrayList<>())
                        .destinationTransactions(new ArrayList<>())
                        .build()
        );


        // Guardar las cuentas en el repositorio
        accountRepository.saveAll(accountList); // Guardar las cuentas
    }
}
