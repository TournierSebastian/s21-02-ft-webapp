package com.wallex.financial_platform.entities;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long movementId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column( nullable = false)
    private String description;

    @Column( nullable = false)
    private Double amount;

    @Column( nullable = false)
    private LocalDateTime movementDate;

    @PrePersist
    protected void onCreate() {
        movementDate = LocalDateTime.now();
    };

}
