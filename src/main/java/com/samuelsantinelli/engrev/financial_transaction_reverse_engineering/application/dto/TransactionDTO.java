package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private Double amount;
    private LocalDateTime timestamp;
    private String description;
    private Long sourceAccountId;
    private Long destinationAccountId;

    public static TransactionDTO fromEntity(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                transaction.getDescription(),
                transaction.getSourceAccount().getId(),
                transaction.getDestinationAccount().getId()
        );
    }
}
