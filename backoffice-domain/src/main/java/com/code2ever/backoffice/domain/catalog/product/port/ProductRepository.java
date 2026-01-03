package com.code2ever.backoffice.domain.catalog.product.port;

import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.common.BaseRepository;

public interface ProductRepository extends BaseRepository<Product> {
    boolean existsBySku(String sku);
}
