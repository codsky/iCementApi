package com.icement.api.iCement.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icement.api.iCement.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
