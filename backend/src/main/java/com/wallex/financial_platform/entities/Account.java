package com.wallex.financial_platform.entities;

import java.util.Currency;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.wallex.financial_platform.utils.enums.AccountStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="accounts")
@Getter
@Setter
@Builder

public class Account {
    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 11)
    private Long cbu;
    private String alias;

    // @Column(name="available_balance")
    // private Double balance;
    
    // @Column(name="reserved_balance")
    // private Double reservedBalance;

    private Currency currency;

    @CreationTimestamp
    @Column(name="creation_date")
    private Date creationDate;

    private AccountStatus status;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="transactions", 
        joinColumns = @JoinColumn(name="source_account_id"),
        inverseJoinColumns = @JoinColumn(name="id")
    )
    private List<Transaction> sendedTransactions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="transactions", 
        joinColumns = @JoinColumn(name="destination_account_id"),
        inverseJoinColumns = @JoinColumn(name="id")
    )
    private List<Transaction> receivedTransactions;

}
