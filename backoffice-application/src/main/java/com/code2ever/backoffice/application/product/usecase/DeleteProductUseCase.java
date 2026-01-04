package com.code2ever.backoffice.application.product.usecase;

import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteProductUseCase {

    private ProductRepository repository;

    public DeleteProductUseCase() {
    }

    @Inject
    public DeleteProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found: " + id));

        repository.delete(product);
    }
}
