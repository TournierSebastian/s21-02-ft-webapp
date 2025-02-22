package com.wallex.financial_platform.entities;

import java.util.Date;
import java.util.List;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
@Getter
@Setter
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @Column(name="full_name")
    private String fullName;

    private String email;
    private String phone;
    private String password;
    
    @CreationTimestamp
    @Column(name="registration_date")
    private Date registrationDate;

    @OneToMany
    private List<Account> account;
}