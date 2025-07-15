package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository;


import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Client;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure.ClientJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryAdapter implements ClientRepository {

    private final ClientJpaRepository jpa;

    public ClientRepositoryAdapter(ClientJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Client save(Client client) {
        return jpa.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return jpa.findAll();
    }
}