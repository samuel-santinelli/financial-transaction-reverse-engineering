package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountId(Long accountId);

    List<Transaction> findByDestinationAccountId(Long accountId);

}
