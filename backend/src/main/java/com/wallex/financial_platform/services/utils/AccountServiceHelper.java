package com.wallex.financial_platform.services.utils;

import com.wallex.financial_platform.dtos.requests.CheckAccountRequestDto;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceHelper {

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public Account createFakeAccount(CheckAccountRequestDto chkAcc) {
        Faker faker = new Faker();
        User savedUser = userRepository.save(this.generateFakeUser());
        Account account = Account.builder()
                .accountId(null)
                .cbu(chkAcc.cbu() == null || chkAcc.cbu().isBlank()
                        ? faker.numerify("CBU000" + "0351" + "Ø" + "00000########" + "Ø")
                        : chkAcc.cbu()
                )
                .alias(chkAcc.alias() == null || chkAcc.alias().isBlank()
                        ? (faker.animal().name()+"."+faker.construction().materials()+"."+faker.house().furniture()).toLowerCase().replace(" ","")
                        : chkAcc.alias()
                )
                .availableBalance(new BigDecimal(0))
                .reservedBalance(new BigDecimal(0))
                .currency(CurrencyType.values()[(int) (Math.random() * 2)])
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(savedUser)
                .reservations(null)
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .reservations(new ArrayList<>())
                .build();
        return account;
    }

    private User generateFakeUser() {
        Faker faker = new Faker();
        return User.builder()
            .fullName(faker.name().fullName())
            .dni(faker.numerify("Ø#######"))
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.numerify("+54##########"))
            .password(passwordEncoder.encode("qwertyui"))
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .accounts(new ArrayList<>())
            .notifications(new ArrayList<>())
            .cards(new ArrayList<>())
            .active(true)
            .build();
    }

}
