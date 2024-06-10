package com.example.wantedchallenge.infrastructure.transaction;

import com.example.wantedchallenge.domain.transaction.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {
//    private final JPAQueryFactory queryFactory;

    @Override
    public List<Transaction> findCustomTransactions() {
//        QTransaction transaction = QTransaction.transaction;
//        return queryFactory.selectFrom(transaction)
//                .where(transaction.status.eq("COMPLETED"))
//                .fetch();
        return  null;
    }
}