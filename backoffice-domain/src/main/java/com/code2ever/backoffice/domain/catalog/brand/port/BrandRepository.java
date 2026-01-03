package com.code2ever.backoffice.domain.catalog.brand.port;

import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.common.BaseRepository;

import java.util.Optional;

public interface BrandRepository extends BaseRepository<Brand> {

    Optional<Brand> findByNameIgnoreCase(String name);

}
