package com.example.wantedchallenge.infrastructure.product;

import com.example.wantedchallenge.domain.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
//    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findCustomProducts() {
//        QProduct product = QProduct.product;
//        return queryFactory.selectFrom(product)
//                .where(product.name.contains("example"))
//                .fetch();
        return null;
    }
}