package com.example.wantedchallenge.domain.product;

import com.example.wantedchallenge.domain.BaseTimeEntity;
import com.example.wantedchallenge.domain.enums.ProductStatus;
import com.example.wantedchallenge.domain.transaction.Transaction;
import com.example.wantedchallenge.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product  extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @OneToMany(mappedBy = "product")
    private List<Transaction> transactions;

}
