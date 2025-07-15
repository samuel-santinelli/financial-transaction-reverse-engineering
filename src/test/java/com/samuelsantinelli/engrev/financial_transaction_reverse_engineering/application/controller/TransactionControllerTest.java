package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.controller;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto.TransactionDTO;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto.TransactionRequestDTO;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void createTransaction_ShouldReturnTransactionDTO() throws Exception {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(1L);
        dto.setSourceAccountId(1L);
        dto.setDestinationAccountId(2L);
        dto.setAmount(100.0);
        dto.setDescription("Test transaction");

        when(transactionService.createTransaction(
                Mockito.anyLong(),
                Mockito.anyLong(),
                Mockito.anyDouble(),
                Mockito.anyString()))
                .thenReturn(dto);

        String jsonRequest = """
            {
                "sourceAccountId": 1,
                "destinationAccountId": 2,
                "amount": 100.0,
                "description": "Test transaction"
            }
        """;

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.sourceAccountId").value(1))
                .andExpect(jsonPath("$.destinationAccountId").value(2))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.description").value("Test transaction"));
    }
}
