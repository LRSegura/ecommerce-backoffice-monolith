package com.code2ever.backoffice.domain.catalog.port;

import com.code2ever.backoffice.domain.catalog.model.Brand;
import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> findById(Long id);
}
