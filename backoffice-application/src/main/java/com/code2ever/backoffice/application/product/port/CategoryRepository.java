package com.code2ever.backoffice.application.product.port;

import com.code2ever.backoffice.domain.catalog.Category;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Long id);
}
