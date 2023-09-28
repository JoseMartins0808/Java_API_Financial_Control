package com.br.martins.learningspringboot.services.Impl;

import com.br.martins.learningspringboot.dto.CreateTransactionDto;
import com.br.martins.learningspringboot.exceptions.AppException;
import com.br.martins.learningspringboot.models.Transaction;
import com.br.martins.learningspringboot.models.User;
import com.br.martins.learningspringboot.repositories.TransactionRepository;
import com.br.martins.learningspringboot.repositories.UserRepository;
import com.br.martins.learningspringboot.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction (final CreateTransactionDto transactionData) {

        final User userPayer = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() ->
            new AppException("payerNotFound", HttpStatus.NOT_FOUND));

        if (Objects.equals(userPayer.getType(), "SELLER")) {
            throw new AppException("invalidUserType", HttpStatus.FORBIDDEN);
        }

        final User userPayee = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() ->
            new AppException("payeeNotFound", HttpStatus.NOT_FOUND));

        final float currentPayerBalance = userPayer.getBalance();

        if (currentPayerBalance < transactionData.getTransaction_value()) {
            throw new AppException("insufficientFunds", HttpStatus.FORBIDDEN);
        }

        final float currentPayeeBalance = userPayee.getBalance();

        userPayer.setBalance(currentPayerBalance - transactionData.getTransaction_value());
        userPayee.setBalance(currentPayeeBalance + transactionData.getTransaction_value());

        userRepository.save(userPayer);
        userRepository.save(userPayee);

        final Transaction newTransaction = new Transaction(userPayer, userPayee, transactionData.getTransaction_value());

        return transactionRepository.save(newTransaction);
    }

    public Transaction retrieveTransaction (final long transactionId) {

        final Transaction transactionFound = transactionRepository.findById(transactionId).orElseThrow(() ->
            new AppException("transactionNotFound", HttpStatus.NOT_FOUND));

        return transactionFound;
    }
}
