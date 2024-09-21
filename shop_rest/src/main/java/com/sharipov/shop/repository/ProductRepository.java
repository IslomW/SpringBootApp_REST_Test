package com.sharipov.shop.repository;

import com.sharipov.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Integer productId);

    void deleteById(Integer id);
}
