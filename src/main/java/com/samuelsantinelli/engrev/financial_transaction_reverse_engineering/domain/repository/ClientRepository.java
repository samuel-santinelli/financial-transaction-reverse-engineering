package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository;


import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Client;

import java.util.Optional;
import java.util.List;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findById(Long id);

    List<Client> findAll();
}
