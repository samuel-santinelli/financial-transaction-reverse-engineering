package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.TransactionRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionJpaRepository jpa;

    public TransactionRepositoryAdapter(TransactionJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return jpa.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return jpa.findAll();
    }

    @Override
    public long count() {
        return jpa.count();
    }

    @Override
    public List<Transaction> findBySourceAccountId(Long accountId) {
        return jpa.findBySourceAccountId(accountId);
    }

    @Override
    public List<Transaction> findByDestinationAccountId(Long accountId) {
        return jpa.findByDestinationAccountId(accountId);
    }
}
