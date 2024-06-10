package com.example.wantedchallenge.infrastructure.product;

import com.example.wantedchallenge.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    // 필요한 추가 메서드를 정의할 수 있습니다.
}
