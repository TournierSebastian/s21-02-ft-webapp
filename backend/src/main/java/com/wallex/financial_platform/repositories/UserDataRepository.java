package com.wallex.financial_platform.repositories;

import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends JpaRepository<User, Long> {
}
