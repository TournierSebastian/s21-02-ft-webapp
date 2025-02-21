package com.wallex.financial_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wallex.financial_platform.entities.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement,Long>{

}
