package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.service;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto.TransactionDTO;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.AccountRepository;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionDTO createTransaction(Long sourceAccountId, Long destinationAccountId,
                                            Double amount, String description) {

        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Optional<Account> sourceOpt = accountRepository.findById(sourceAccountId);
        Optional<Account> destOpt = accountRepository.findById(destinationAccountId);

        if (sourceOpt.isEmpty() || destOpt.isEmpty()) {
            throw new IllegalArgumentException("Source or destination account not found");
        }

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceOpt.get());
        transaction.setDestinationAccount(destOpt.get());
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTimestamp(LocalDateTime.now());

        Transaction saved = transactionRepository.save(transaction);

        return toDTO(saved);
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setTimestamp(transaction.getTimestamp());
        dto.setDescription(transaction.getDescription());
        dto.setSourceAccountId(transaction.getSourceAccount().getId());
        dto.setDestinationAccountId(transaction.getDestinationAccount().getId());
        return dto;
    }
}
