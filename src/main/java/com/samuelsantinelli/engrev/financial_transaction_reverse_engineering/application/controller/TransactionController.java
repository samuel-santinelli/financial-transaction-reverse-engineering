package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.controller;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto.TransactionDTO;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto.TransactionRequestDTO;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.service.TransactionService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(
            summary = "Create a new transaction",
            description = "Creates a transaction transferring amount from source account to destination account",
            // Aqui, use o RequestBody do Swagger completamente qualificado
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Transaction data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TransactionRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction created successfully",
                            content = @Content(schema = @Schema(implementation = TransactionDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping
    // Este @RequestBody deve ser o do Spring
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionRequestDTO request) {
        TransactionDTO transaction = transactionService.createTransaction(
                request.getSourceAccountId(),
                request.getDestinationAccountId(),
                request.getAmount(),
                request.getDescription()
        );
        return ResponseEntity.ok(transaction);
    }

    @Operation(
            summary = "Get all transactions",
            description = "Returns a list of all transactions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of transactions",
                            content = @Content(schema = @Schema(implementation = TransactionDTO.class)))
            }
    )
    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}