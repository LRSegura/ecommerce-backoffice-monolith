package com.code2ever.backoffice.application.product.usecase;

import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.application.product.dto.CreateProductCommand;
import com.code2ever.backoffice.application.product.dto.ProductView;
import com.code2ever.backoffice.application.product.mapper.ProductViewMapper;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.model.ProductStatus;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CreateProductUseCase {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    public CreateProductUseCase(){
    }

    @Inject
    public CreateProductUseCase(ProductRepository productRepository,
                                BrandRepository brandRepository,
                                CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ProductView execute(@Valid CreateProductCommand dto) {
        if (productRepository.existsBySku(dto.sku())) {
            throw new BusinessRuleException("SKU already exists: " + dto.sku());
        }

        Brand brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new NotFoundException("Brand not found: " + dto.brandId()));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found: " + dto.categoryId()));

        Product product = new Product();
        product.setSku(dto.sku());
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setBrand(brand);
        product.setCategory(category);
        product.setStatus(ProductStatus.ACTIVE);

        productRepository.save(product);

        return ProductViewMapper.toView(product, dto.initialPrice());
    }
}
