package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
}