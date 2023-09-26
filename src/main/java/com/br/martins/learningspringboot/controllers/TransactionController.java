package com.br.martins.learningspringboot.controllers;

import com.br.martins.learningspringboot.dto.CreateTransactionDto;
import com.br.martins.learningspringboot.models.Transaction;
import com.br.martins.learningspringboot.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController (TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction (@Valid @RequestBody final CreateTransactionDto transactionData) {

        final Transaction createTransaction = transactionService.createTransaction(transactionData);

        return new ResponseEntity<Transaction>(createTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> retrieveTransaction (@PathVariable final String transactionId) {

        final Transaction transactionFound = transactionService.retrieveTransaction(Long.parseLong(transactionId));

        return new ResponseEntity<Transaction>(transactionFound, HttpStatus.OK);
    }

}
