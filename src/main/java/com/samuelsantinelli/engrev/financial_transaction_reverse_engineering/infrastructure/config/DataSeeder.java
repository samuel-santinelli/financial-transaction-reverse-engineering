package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.infrastructure.config;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Client;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.AccountRepository;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.ClientRepository;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository,
                                   AccountRepository accountRepository,
                                   TransactionRepository transactionRepository) {
        return args -> {
            if (clientRepository.findAll().isEmpty()) {

                // Criando clientes
                Client alice = Client.builder().name("Alice").build();
                Client bob = Client.builder().name("Bob").build();
                Client carol = Client.builder().name("Carol").build();

                alice = clientRepository.save(alice);
                bob = clientRepository.save(bob);
                carol = clientRepository.save(carol);

                // Criando contas
                Account accountAlice = Account.builder().number("123-456").owner(alice).build();
                Account accountBob = Account.builder().number("987-654").owner(bob).build();
                Account accountCarol = Account.builder().number("111-222").owner(carol).build();

                accountAlice = accountRepository.save(accountAlice);
                accountBob = accountRepository.save(accountBob);
                accountCarol = accountRepository.save(accountCarol);

                // Criando transações
                Transaction tx1 = Transaction.builder()
                        .amount(500.0)
                        .timestamp(LocalDateTime.now().minusDays(2))
                        .description("Transfer to savings")
                        .sourceAccount(accountAlice)
                        .destinationAccount(accountBob)
                        .build();

                Transaction tx2 = Transaction.builder()
                        .amount(200.0)
                        .timestamp(LocalDateTime.now().minusDays(1))
                        .description("Payment for services")
                        .sourceAccount(accountBob)
                        .destinationAccount(accountCarol)
                        .build();

                Transaction tx3 = Transaction.builder()
                        .amount(150.0)
                        .timestamp(LocalDateTime.now())
                        .description("Refund")
                        .sourceAccount(accountCarol)
                        .destinationAccount(accountAlice)
                        .build();

                transactionRepository.save(tx1);
                transactionRepository.save(tx2);
                transactionRepository.save(tx3);

                System.out.println("Seed data: Clients, Accounts and Transactions loaded.");
            } else {
                System.out.println("Data already exists, skipping seeding.");
            }
        };
    }
}
