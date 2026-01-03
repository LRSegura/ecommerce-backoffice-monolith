package com.code2ever.backoffice.application.brand.mapper;

import com.code2ever.backoffice.application.brand.dto.BrandCreateRequest;
import com.code2ever.backoffice.application.brand.dto.BrandResponse;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrandMapper {

    public Brand toNewEntity(BrandCreateRequest req) {
        Brand brand = new Brand();
        brand.setName(req.name());
        brand.setActive(req.active() == null || req.active());
        return brand;
    }

    public BrandResponse toResponse(Brand brand) {
        return new BrandResponse(
                brand.getId(),
                brand.getName(),
                brand.getActive()
        );
    }
}
