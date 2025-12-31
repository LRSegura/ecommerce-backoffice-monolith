package com.code2ever.backoffice.application.product.port;

import com.code2ever.backoffice.domain.catalog.Product;
import java.math.BigDecimal;

public interface ProductPriceRepository {
    BigDecimal findCurrentPrice(Product product);
    void setNewCurrentPrice(Product product, BigDecimal newPrice);
}
