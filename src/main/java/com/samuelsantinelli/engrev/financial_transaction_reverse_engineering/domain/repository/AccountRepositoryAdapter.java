package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountRepositoryAdapter implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;

    public AccountRepositoryAdapter(JpaAccountRepository jpaAccountRepository) {
        this.jpaAccountRepository = jpaAccountRepository;
    }

    @Override
    public Account save(Account account) {
        return jpaAccountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return jpaAccountRepository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return jpaAccountRepository.findAll();
    }

    @Override
    public long count() {
        return jpaAccountRepository.count();
    }
}