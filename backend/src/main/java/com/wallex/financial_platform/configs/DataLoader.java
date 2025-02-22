package com.wallex.financial_platform.configs;

import com.wallex.financial_platform.configs.data.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(UserDataLoader userDataLoader,
                                      AccountDataLoader accountDataLoader,
                                      NotificationDataLoader notificationDataLoader,
                                      CardDataLoader cardDataLoader
                                      //ReservationDataLoader reservationDataLoader,
                                     // MovementDataLoader movementDataLoader
                                      ) {

        return args -> {
            userDataLoader.load();
            accountDataLoader.load();
            notificationDataLoader.load();
            cardDataLoader.load();
            //reservationDataLoader.load();
            //movementDataLoader.load();
        };
    }
}
