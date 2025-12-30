package com.code2ever.backoffice.application.product.repository;

import com.code2ever.backoffice.domain.catalog.Brand;
import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> findById(Long id);
}
