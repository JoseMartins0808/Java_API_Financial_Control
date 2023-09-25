package com.br.martins.learningspringboot.services;

import com.br.martins.learningspringboot.dto.CreateTransactionDto;
import com.br.martins.learningspringboot.models.Transaction;
import com.br.martins.learningspringboot.models.User;
import com.br.martins.learningspringboot.repositories.TransactionRepository;
import com.br.martins.learningspringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    public TransactionService (TransactionRepository transactionRepository,
        UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction (final CreateTransactionDto transactionData) throws Exception {

        final User userPayer = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() ->
            new Exception("User not found"));

        final User userPayee = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() ->
            new Exception("User not found"));

        final Transaction newTransaction = new Transaction(userPayer, userPayee, transactionData.getTransaction_value());

        return transactionRepository.save(newTransaction);
    }

    public Transaction retrieveTransaction (final long transactionId) throws Exception {

        final Transaction transactionFound = transactionRepository.findById(transactionId).orElseThrow(() ->
            new Exception("Transaction not found"));

        return transactionFound;
    }
}
