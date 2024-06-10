package com.example.wantedchallenge.domain.transaction;

import com.example.wantedchallenge.domain.BaseTimeEntity;
import com.example.wantedchallenge.domain.enums.TransactionStatus;
import com.example.wantedchallenge.domain.product.Product;
import com.example.wantedchallenge.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(nullable = false)
    private BigDecimal priceAtPurchase;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


}
