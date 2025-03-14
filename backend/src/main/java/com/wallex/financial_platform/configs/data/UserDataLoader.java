package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDataLoader {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void load() {
        Faker faker = new Faker();

        // Crear usuarios con contraseñas encriptadas
        User user1 = new User(
                null,
                "Juan Pérez",
                "12345678",
                "jindrg@gmail.com",
                "+541112345678",
                passwordEncoder.encode("password123"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                null,
                null,
                null,
                null,
                false
        );

        User user2 = new User(
                null,
                "María Gómez",
                "87654321",
                "maria.gomez@dominio.com",
                "+541198765432",
                passwordEncoder.encode("password456"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                null,
                null,
                null,
                null,
                false
        );

        User user3 = new User(
                null,
                "Carlos López",
                "56789123",
                "carlos.lopez@dominio.com",
                "+541112345679",
                passwordEncoder.encode("password789"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                null,
                null,
                null,
                null,
                false
        );

        User user4 = new User(
                null,
                "Ana Torres",
                "23456789",
                "ana.torres@dominio.com",
                "+541112345680",
                passwordEncoder.encode("password012"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                null,
                null,
                null,
                null,
                false
        );

        User user5 = new User(
                null,
                "Luis Méndez",
                "34567891",
                "luis.mendez@dominio.com",
                "+541112345681",
                passwordEncoder.encode("password345"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                null,
                null,
                null,
                null,
                false
        );

        User user6 = new User(
                null,
                "MERCADO PAGO SERVICIOS DE PROCESAMIENTO S.R.L",
                "71699949",
                "support@mercadopago.com",
                faker.numerify("+54##########"),
                passwordEncoder.encode("password345"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                null,
                null,
                null,
                null,
                false
        );

        User user7 = new User(
                null,
                "Tesoreria Wallex",
                "71221356",
                "tesoreria@wallex.com",
                faker.numerify("+54##########"),
                passwordEncoder.encode("qwertyui"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                null,
                null,
                null,
                null,
                false
        );


        User user8 = new User(
                null,
                "Visa",
                "71135628",
                "visa@testing.com",
                faker.numerify("+54##########"),
                passwordEncoder.encode("qwertyui"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                null,
                null,
                null,
                null,
                true
        );

        User user9 = new User(
                null,
                "MasterCard",
                "71003569",
                "mastercard@testing.com",
                faker.numerify("+54##########"),
                passwordEncoder.encode("qwertyui"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                null,
                null,
                null,
                null,
                true
        );

        User user10 = new User(
                null,
                "American Express",
                "73100620",
                "amex@testing.com",
                faker.numerify("+54##########"),
                passwordEncoder.encode("qwertyui"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                null,
                null,
                null,
                null,
                true
        );

        userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
    }

}
