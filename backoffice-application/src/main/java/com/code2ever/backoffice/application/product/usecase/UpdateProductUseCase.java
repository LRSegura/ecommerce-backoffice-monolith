package com.code2ever.backoffice.application.product.usecase;

import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.application.product.dto.UpdateProductCommand;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UpdateProductUseCase {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    public UpdateProductUseCase(){
    }

    @Inject
    public UpdateProductUseCase(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void execute(Long productId, @Valid UpdateProductCommand dto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found: " + productId));

        Brand brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new NotFoundException("Brand not found: " + dto.brandId()));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found: " + dto.categoryId()));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setBrand(brand);
        product.setCategory(category);

        productRepository.save(product);
    }
}
