package com.example.wantedchallenge.infrastructure.transaction;

import com.example.wantedchallenge.domain.transaction.Transaction;

import java.util.List;

public interface TransactionRepositoryCustom {
    List<Transaction> findCustomTransactions();
}
