package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceAccountId(Long sourceAccountId);
    List<Transaction> findByDestinationAccountId(Long destinationAccountId);
}