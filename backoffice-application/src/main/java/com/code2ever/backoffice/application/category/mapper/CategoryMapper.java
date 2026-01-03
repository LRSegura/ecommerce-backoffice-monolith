package com.code2ever.backoffice.application.category.mapper;

import com.code2ever.backoffice.application.category.dto.CategoryCreateRequest;
import com.code2ever.backoffice.application.category.dto.CategoryResponse;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryMapper {

    public Category toNewEntity(CategoryCreateRequest req) {
        Category category = new Category();
        category.setName(req.name());
        category.setActive(req.active() == null || req.active());
        return category;
    }

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getActive()
        );
    }
}
