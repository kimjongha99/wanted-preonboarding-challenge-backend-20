package com.example.wantedchallenge.infrastructure.transaction;

import com.example.wantedchallenge.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryCustom {
    // 필요한 추가 메서드를 정의할 수 있습니다.
}
