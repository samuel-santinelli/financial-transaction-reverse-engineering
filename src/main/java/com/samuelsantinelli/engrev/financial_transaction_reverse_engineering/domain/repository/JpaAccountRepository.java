package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<Account, Long> {
}
