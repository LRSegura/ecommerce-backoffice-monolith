package com.code2ever.backoffice.application.brand.usecase;

import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteBrandUseCase {

    private BrandRepository repository;
    
    
    public DeleteBrandUseCase(){
        
    }

    public DeleteBrandUseCase(BrandRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void execute(Long id) {
        Brand brand = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found: " + id));

        if (brand.getProducts() != null && !brand.getProducts().isEmpty()) {
            throw new BusinessRuleException("Cannot delete brand with existing products");
        }

        repository.delete(brand);
    }

    
}
