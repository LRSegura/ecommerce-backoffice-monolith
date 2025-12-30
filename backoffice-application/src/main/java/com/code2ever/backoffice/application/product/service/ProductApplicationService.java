package com.code2ever.backoffice.application.product.service;

import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.application.product.dto.DtoChangeProductPrice;
import com.code2ever.backoffice.application.product.dto.DtoCreateProduct;
import com.code2ever.backoffice.application.product.dto.DtoProductView;
import com.code2ever.backoffice.application.product.dto.DtoUpdateProduct;
import com.code2ever.backoffice.application.product.repository.BrandRepository;
import com.code2ever.backoffice.application.product.repository.CategoryRepository;
import com.code2ever.backoffice.application.product.repository.ProductPriceRepository;
import com.code2ever.backoffice.application.product.repository.ProductRepository;
import com.code2ever.backoffice.domain.catalog.Brand;
import com.code2ever.backoffice.domain.catalog.Category;
import com.code2ever.backoffice.domain.catalog.Product;
import com.code2ever.backoffice.domain.catalog.ProductStatus;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.math.BigDecimal;

public class ProductApplicationService {

    @Inject
    ProductRepository productRepository;

    @Inject
    BrandRepository brandRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    ProductPriceRepository productPriceRepository;

    @Transactional
    public Long create(@Valid DtoCreateProduct dto) {
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

        product = productRepository.save(product);

        productPriceRepository.setNewCurrentPrice(product, dto.initialPrice());

        return product.getId();
    }

    @Transactional
    public void update(Long productId, @Valid DtoUpdateProduct dto) {
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

    @Transactional
    public void changePrice(Long productId, @Valid DtoChangeProductPrice dto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found: " + productId));

        if (product.isInactive()) {
            throw new BusinessRuleException("Cannot change price of an inactive product. id=" + productId);
        }

        productPriceRepository.setNewCurrentPrice(product, dto.newPrice());
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public DtoProductView findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found: " + productId));

        Long id = product.getId();
        String sku = product.getSku();
        String name = product.getName();
        String brandName = product.getBrand() != null ? product.getBrand().getName() : null;
        String categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
        BigDecimal currentPrice = productPriceRepository.findCurrentPrice(product);
        String status = product.getStatus().getDescription();

        return new DtoProductView(id, sku, name, brandName, categoryName, currentPrice, status);
    }
}
