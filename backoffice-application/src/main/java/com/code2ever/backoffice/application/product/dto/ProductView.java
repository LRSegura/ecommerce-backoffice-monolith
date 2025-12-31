package com.code2ever.backoffice.application.product.dto;

import java.math.BigDecimal;

public record ProductView(
        Long id,
        String sku,
        String name,
        String brandName,
        String categoryName,
        BigDecimal currentPrice,
        String status
) {
}
