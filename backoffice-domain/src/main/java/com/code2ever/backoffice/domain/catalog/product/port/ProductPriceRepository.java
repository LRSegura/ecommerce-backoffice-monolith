package com.code2ever.backoffice.domain.catalog.product.port;

import com.code2ever.backoffice.domain.catalog.product.model.PriceType;
import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.common.BaseRepository;
import com.code2ever.backoffice.domain.catalog.product.model.ProductPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductPriceRepository extends BaseRepository<ProductPrice> {

    Optional<ProductPrice> findActivePrice(Long productId, PriceType type, LocalDateTime atDate);


    List<ProductPrice> findByProductAndType(Long productId, PriceType type);


    List<ProductPrice> findOverlappingPrices(Long productId, PriceType type, LocalDateTime from, LocalDateTime to);


    BigDecimal findCurrentPriceValue(Product product);
}