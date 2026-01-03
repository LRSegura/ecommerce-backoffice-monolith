package com.code2ever.backoffice.application.product.mapper;

import com.code2ever.backoffice.application.product.dto.ProductView;
import com.code2ever.backoffice.domain.catalog.product.model.Product;

import java.math.BigDecimal;

public class ProductViewMapper {
    private ProductViewMapper() {}

    public static ProductView toView(Product product, BigDecimal currentPrice) {
        return new ProductView(product.getId(),
                product.getSku(),
                product.getName(),
                product.getBrand().getName(),
                product.getCategory().getName(),
                currentPrice,
                product.getStatus().getDescription());
    }
}
