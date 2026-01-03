package com.code2ever.backoffice.application.brand.usecase;

import com.code2ever.backoffice.application.brand.dto.BrandCreateRequest;
import com.code2ever.backoffice.application.brand.dto.BrandResponse;
import com.code2ever.backoffice.application.brand.mapper.BrandMapper;
import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateBrandUseCase {

    private BrandRepository repository;
    private BrandMapper mapper;

    public CreateBrandUseCase(){
    }

    @Inject
    public CreateBrandUseCase(BrandRepository repository, BrandMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public BrandResponse execute(BrandCreateRequest request) {
        String name = normalize(request.name());

        repository.findByNameIgnoreCase(name)
                .ifPresent(b -> { throw new BusinessRuleException("Brand already exists: " + name); });

        Brand brand = mapper.toNewEntity(new BrandCreateRequest(name, request.active()));
        repository.save(brand);

        return mapper.toResponse(brand);
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) throw new BusinessRuleException("Brand name is required");
        return value.trim();
    }
}
