package com.code2ever.backoffice.domain.catalog.category.port;

import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.common.BaseRepository;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category> {

    Optional<Category> findByNameIgnoreCase(String name);
}
