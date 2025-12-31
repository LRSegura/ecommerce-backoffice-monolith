package com.code2ever.backoffice.application.product.port;

import com.code2ever.backoffice.domain.catalog.Brand;
import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> findById(Long id);
}
