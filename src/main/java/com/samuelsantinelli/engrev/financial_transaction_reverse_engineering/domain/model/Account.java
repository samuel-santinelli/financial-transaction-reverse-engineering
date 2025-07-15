package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;

    @ManyToOne
    @JsonBackReference
    private Client owner;

    @OneToMany(mappedBy = "sourceAccount")
    @JsonManagedReference(value = "outgoing")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "destinationAccount")
    @JsonManagedReference(value = "incoming")
    private List<Transaction> incomingTransactions;
}