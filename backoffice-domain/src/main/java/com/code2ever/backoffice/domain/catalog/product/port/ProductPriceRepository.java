package com.code2ever.backoffice.domain.catalog.product.port;

import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.common.BaseRepository;
import com.code2ever.backoffice.domain.pricing.ProductPrice;

import java.math.BigDecimal;

public interface ProductPriceRepository extends BaseRepository<ProductPrice> {
    BigDecimal findCurrentPrice(Product product);
    void setNewCurrentPrice(Product product, BigDecimal newPrice);
}
