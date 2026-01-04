package com.code2ever.backoffice.application.category.usecase;

import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteCategoryUseCase {

    private CategoryRepository repository;

    public DeleteCategoryUseCase() {
    }

    @Inject
    public DeleteCategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));

        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new BusinessRuleException("Cannot delete category with existing products");
        }

        repository.delete(category);
    }
}