package com.br.martins.learningspringboot.services;

import com.br.martins.learningspringboot.dto.CreateTransactionDto;
import com.br.martins.learningspringboot.models.Transaction;

public interface TransactionService {

    Transaction createTransaction (final CreateTransactionDto transactionData);

    Transaction retrieveTransaction (final long transactionId);
}
