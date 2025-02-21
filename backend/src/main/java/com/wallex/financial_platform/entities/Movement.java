package com.wallex.financial_platform.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
@Table(name="movements")
@Getter
@Setter
@AllArgsConstructor*/
@Entity
public class Movement {
/*    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movement_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    private String description;
    private Double amount;

    @CreationTimestamp
    @Column(name="movement_date")
    private Date movementDate;
    */
}
