package com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.service;

import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.application.dto.TransactionDTO;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Account;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.model.Transaction;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.AccountRepository;
import com.samuelsantinelli.engrev.financial_transaction_reverse_engineering.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        accountRepository = mock(AccountRepository.class);
        transactionService = new TransactionService(transactionRepository, accountRepository);
    }

    @Test
    void createTransaction_ShouldSaveTransaction_WhenValidInput() {
        // Arrange
        Long sourceId = 1L;
        Long destId = 2L;
        Double amount = 100.0;
        String description = "Test Transaction";

        Account sourceAccount = new Account();
        sourceAccount.setId(sourceId);

        Account destAccount = new Account();
        destAccount.setId(destId);

        when(accountRepository.findById(sourceId)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(destId)).thenReturn(Optional.of(destAccount));

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        when(transactionRepository.save(captor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        TransactionDTO result = transactionService.createTransaction(sourceId, destId, amount, description);

        // Assert
        assertNotNull(result);
        assertEquals(sourceId, result.getSourceAccountId());
        assertEquals(destId, result.getDestinationAccountId());
        assertEquals(amount, result.getAmount());
        assertEquals(description, result.getDescription());
        assertNotNull(result.getTimestamp());

        verify(accountRepository, times(1)).findById(sourceId);
        verify(accountRepository, times(1)).findById(destId);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void createTransaction_ShouldThrowException_WhenAmountIsNullOrNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(1L, 2L, null, "desc"));

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(1L, 2L, -50.0, "desc"));

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(1L, 2L, 0.0, "desc"));
    }

    @Test
    void createTransaction_ShouldThrowException_WhenAccountNotFound() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(1L, 2L, 100.0, "desc"));
    }
    @Test
    void getAllTransactions_ShouldReturnListOfTransactionDTO() {
        // Arrange
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);

        Account destAccount = new Account();
        destAccount.setId(2L);

        Transaction tx1 = new Transaction();
        tx1.setId(100L);
        tx1.setSourceAccount(sourceAccount);
        tx1.setDestinationAccount(destAccount);
        tx1.setAmount(200.0);
        tx1.setDescription("First transaction");
        tx1.setTimestamp(LocalDateTime.now());

        Transaction tx2 = new Transaction();
        tx2.setId(101L);
        tx2.setSourceAccount(sourceAccount);
        tx2.setDestinationAccount(destAccount);
        tx2.setAmount(300.0);
        tx2.setDescription("Second transaction");
        tx2.setTimestamp(LocalDateTime.now());

        when(transactionRepository.findAll()).thenReturn(List.of(tx1, tx2));

        // Act
        var resultList = transactionService.getAllTransactions();

        // Assert
        assertNotNull(resultList);
        assertEquals(2, resultList.size());

        TransactionDTO dto1 = resultList.get(0);
        assertEquals(tx1.getId(), dto1.getId());
        assertEquals(tx1.getAmount(), dto1.getAmount());
        assertEquals(tx1.getDescription(), dto1.getDescription());
        assertEquals(tx1.getSourceAccount().getId(), dto1.getSourceAccountId());
        assertEquals(tx1.getDestinationAccount().getId(), dto1.getDestinationAccountId());

        TransactionDTO dto2 = resultList.get(1);
        assertEquals(tx2.getId(), dto2.getId());
        assertEquals(tx2.getAmount(), dto2.getAmount());
        assertEquals(tx2.getDescription(), dto2.getDescription());
        assertEquals(tx2.getSourceAccount().getId(), dto2.getSourceAccountId());
        assertEquals(tx2.getDestinationAccount().getId(), dto2.getDestinationAccountId());

        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void getAllTransactions_ShouldReturnEmptyList_WhenNoTransactions() {
        // Arrange
        when(transactionRepository.findAll()).thenReturn(List.of());

        // Act
        List<TransactionDTO> transactions = transactionService.getAllTransactions();

        // Assert
        assertNotNull(transactions);
        assertTrue(transactions.isEmpty());

        verify(transactionRepository, times(1)).findAll();
    }

}
