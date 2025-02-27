package com.wallex.financial_platform.entities.listeners;

import com.wallex.financial_platform.entities.Transaction;
import jakarta.persistence.PostPersist;

public class TransactionListener {

    @PostPersist
    private void generateMovement(Transaction transaction){

    }
}
