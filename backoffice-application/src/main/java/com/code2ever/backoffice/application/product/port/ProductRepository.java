package com.code2ever.backoffice.application.product.port;

import com.code2ever.backoffice.domain.catalog.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
    boolean existsBySku(String sku);
    Product save(Product product);
}
