package com.wallex.financial_platform.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wallex.financial_platform.entities.enums.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "cards")
@AllArgsConstructor @NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String encryptedNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType type;

    @ManyToOne
    @JoinColumn(name="user_provider_id", nullable = false)
    private User provider;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false)
    private String encryptedCvv;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="card_transactions",
            joinColumns = @JoinColumn(name="card_id"),
            inverseJoinColumns = @JoinColumn(name="transaction_id"))
    private List<Transaction> transactions;

    @PrePersist
    protected void onCreate() {
        registrationDate = LocalDateTime.now();
    }
}
