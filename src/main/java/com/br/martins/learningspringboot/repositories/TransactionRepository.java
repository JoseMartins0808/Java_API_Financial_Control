package com.br.martins.learningspringboot.repositories;

import com.br.martins.learningspringboot.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
