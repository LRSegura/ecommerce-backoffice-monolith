package com.code2ever.backoffice.application.brand.usecase;

import com.code2ever.backoffice.application.brand.dto.BrandResponse;
import com.code2ever.backoffice.application.brand.dto.BrandUpdateRequest;
import com.code2ever.backoffice.application.brand.mapper.BrandMapper;
import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateBrandUseCase {

    private BrandRepository repository;
    private BrandMapper mapper;

    public UpdateBrandUseCase(){
    }

    @Inject
    public UpdateBrandUseCase(BrandRepository repository, BrandMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public BrandResponse execute(Long id, BrandUpdateRequest request) {
        Brand brand = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found: " + id));

        if (request.name() != null) {
            String newName = normalize(request.name());

            repository.findByNameIgnoreCase(newName)
                    .filter(existing -> !existing.getId().equals(id))
                    .ifPresent(b -> { throw new BusinessRuleException("Brand name already in use: " + newName); });

            brand.setName(newName);
        }

        if (request.active() != null) {
            brand.setActive(request.active());
        }

        repository.save(brand);
        return mapper.toResponse(brand);
    }

    private String normalize(String value) {
        if (value.isBlank()) throw new BusinessRuleException("Brand name cannot be blank");
        return value.trim();
    }
}
