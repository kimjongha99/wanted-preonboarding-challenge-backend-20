package com.example.wantedchallenge.infrastructure.product;


import com.example.wantedchallenge.domain.product.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findCustomProducts();
}