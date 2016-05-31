package com.rakuten.examples.products.repository;

import com.rakuten.examples.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}