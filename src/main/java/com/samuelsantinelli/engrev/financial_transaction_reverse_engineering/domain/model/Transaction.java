package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDateTime timestamp;
    private String description;

    @ManyToOne
    @JsonBackReference(value = "outgoing")
    private Account sourceAccount;

    @ManyToOne
    @JsonBackReference(value = "incoming")
    private Account destinationAccount;
}
