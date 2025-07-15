package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Client;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.ClientRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository jpa;

    public ClientRepositoryImpl(ClientJpaRepository jpa) {
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