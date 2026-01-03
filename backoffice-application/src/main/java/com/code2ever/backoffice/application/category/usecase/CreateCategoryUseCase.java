package com.code2ever.backoffice.application.category.usecase;

import com.code2ever.backoffice.application.category.dto.CategoryCreateRequest;
import com.code2ever.backoffice.application.category.dto.CategoryResponse;
import com.code2ever.backoffice.application.category.mapper.CategoryMapper;
import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateCategoryUseCase {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    public CreateCategoryUseCase(){
    }

    @Inject
    public CreateCategoryUseCase(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CategoryResponse execute(CategoryCreateRequest request) {
        String name = normalize(request.name());

        repository.findByNameIgnoreCase(name)
                .ifPresent(c -> { throw new BusinessRuleException("Category already exists: " + name); });

        Category category = mapper.toNewEntity(new CategoryCreateRequest(name, request.active()));
        repository.save(category);

        return mapper.toResponse(category);
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) throw new BusinessRuleException("Category name is required");
        return value.trim();
    }
}
