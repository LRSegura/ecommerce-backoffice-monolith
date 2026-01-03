package com.code2ever.backoffice.application.category.usecase;

import com.code2ever.backoffice.application.category.dto.CategoryResponse;
import com.code2ever.backoffice.application.category.dto.CategoryUpdateRequest;
import com.code2ever.backoffice.application.category.mapper.CategoryMapper;
import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateCategoryUseCase {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    public UpdateCategoryUseCase(){
    }

    @Inject
    public UpdateCategoryUseCase(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CategoryResponse execute(Long id, CategoryUpdateRequest request) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));

        if (request.name() != null) {
            String newName = normalize(request.name());

            repository.findByNameIgnoreCase(newName)
                    .filter(existing -> !existing.getId().equals(id))
                    .ifPresent(c -> { throw new BusinessRuleException("Category name already in use: " + newName); });

            category.setName(newName);
        }

        if (request.active() != null) {
            category.setActive(request.active());
        }

        repository.save(category);
        return mapper.toResponse(category);
    }

    private String normalize(String value) {
        if (value.isBlank()) throw new BusinessRuleException("Category name cannot be blank");
        return value.trim();
    }
}
