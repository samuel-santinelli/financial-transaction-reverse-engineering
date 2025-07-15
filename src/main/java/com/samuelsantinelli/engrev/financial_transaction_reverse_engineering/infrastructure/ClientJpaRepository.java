package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {
}
