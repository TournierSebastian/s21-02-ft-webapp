package com.wallex.financial_platform.repositories;

import com.wallex.financial_platform.entities.Card;
import com.wallex.financial_platform.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
}
