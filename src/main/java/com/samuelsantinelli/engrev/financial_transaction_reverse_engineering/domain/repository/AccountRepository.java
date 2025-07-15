package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;

import java.util.Optional;
import java.util.List;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findById(Long id);

    List<Account> findAll();
    long count();
}