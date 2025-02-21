package com.wallex.financial_platform.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.wallex.financial_platform.utils.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="transactions")
@Getter
@Setter
@Builder
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name="source_account_id")
    private Account source;

    @ManyToOne(fetch =FetchType.LAZY)
    @Column(name="destination_account_id")
    private Account destination;

    private Double amount;
    private TransactionType type;
    private String reason;

    @CreationTimestamp
    @Column(name="transaction_date")
    private Date transactionDate;
    
}
