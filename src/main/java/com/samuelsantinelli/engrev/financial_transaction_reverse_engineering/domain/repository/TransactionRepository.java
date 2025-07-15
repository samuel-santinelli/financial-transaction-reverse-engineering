package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    long count();

    List<Transaction> findBySourceAccountId(Long accountId);
    List<Transaction> findByDestinationAccountId(Long accountId);
}
