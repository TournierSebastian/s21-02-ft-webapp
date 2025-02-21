package com.wallex.financial_platform.entities;
import jakarta.validation.constraints.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 11)
    @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    @Column(nullable = false, unique = true, length = 100)
    @Email(message = "El email debe ser válido")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El email debe seguir el formato: ejemplo@dominio.com")
    private String email;

    @Column(nullable = true, unique = true, length = 20)
    @Pattern(regexp = "^\\+54\\d{10}$", message = "El teléfono debe seguir el formato: +54XXXXXXXXXX")
    private String phoneNumber;

    @Column(nullable = false)
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 20)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean active ;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt =  LocalDateTime.now();
        active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}