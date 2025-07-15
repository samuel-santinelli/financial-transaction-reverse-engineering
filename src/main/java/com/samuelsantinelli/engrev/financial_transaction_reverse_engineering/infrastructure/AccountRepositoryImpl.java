package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.AccountRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository jpa;

    public AccountRepositoryImpl(AccountJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Account save(Account account) {
        return jpa.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return jpa.findAll();
    }

    @Override
    public long count() {
        return jpa.count();
    }
}
