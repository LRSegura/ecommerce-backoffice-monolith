package com.code2ever.backoffice.domain.catalog.port;

import com.code2ever.backoffice.domain.catalog.model.Category;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Long id);
}
