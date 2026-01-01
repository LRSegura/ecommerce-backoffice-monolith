package com.code2ever.backoffice.domain.catalog.port;

import com.code2ever.backoffice.domain.catalog.model.Product;
import java.math.BigDecimal;

public interface ProductPriceRepository {
    BigDecimal findCurrentPrice(Product product);
    void setNewCurrentPrice(Product product, BigDecimal newPrice);
}
