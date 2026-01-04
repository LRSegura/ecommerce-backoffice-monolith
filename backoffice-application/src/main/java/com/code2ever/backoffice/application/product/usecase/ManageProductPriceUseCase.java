package com.code2ever.backoffice.application.product.usecase;

import com.code2ever.backoffice.application.common.exception.BusinessRuleException;
import com.code2ever.backoffice.application.common.exception.NotFoundException;
import com.code2ever.backoffice.application.product.dto.CreatePriceCommand;
import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.model.ProductPrice;
import com.code2ever.backoffice.domain.catalog.product.port.ProductPriceRepository;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ManageProductPriceUseCase {

    private ProductPriceRepository priceRepository;
    private ProductRepository productRepository;

    public ManageProductPriceUseCase(){

    }

    @Inject
    public ManageProductPriceUseCase(ProductPriceRepository priceRepository, ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void createPrice(CreatePriceCommand cmd) {
        Product product = productRepository.findById(cmd.productId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        // 1. Validar fechas
        if (cmd.validTo() != null && cmd.validTo().isBefore(cmd.validFrom())) {
            throw new BusinessRuleException("Valid To date cannot be before Valid From date");
        }

        // 2. Manejo de precios anteriores (Lógica de cierre automático para precios LIST)
        // Si agregamos un precio LIST que empieza AHORA, cerramos el anterior activo.
        if (cmd.validFrom().isBefore(LocalDateTime.now().plusMinutes(1))) {
            priceRepository.findActivePrice(cmd.productId(), cmd.type(), LocalDateTime.now())
                    .ifPresent(current -> {
                        // Cerrar el precio anterior justo antes de que empiece el nuevo
                        current.setValidTo(cmd.validFrom());
                        priceRepository.update(current);
                    });
        }

        // 3. Validar solapamientos (especialmente para precios futuros programados)
        List<ProductPrice> overlaps = priceRepository.findOverlappingPrices(
                cmd.productId(), cmd.type(), cmd.validFrom(), cmd.validTo());

        if (!overlaps.isEmpty()) {
            throw new BusinessRuleException("This price overlaps with an existing active price schedule.");
        }

        // 4. Crear nuevo precio
        ProductPrice newPrice = new ProductPrice();
        newPrice.setProduct(product);
        newPrice.setPrice(cmd.price());
        newPrice.setCurrency(cmd.currency());
        newPrice.setPriceType(cmd.type());
        newPrice.setValidFrom(cmd.validFrom());
        newPrice.setValidTo(cmd.validTo());
        newPrice.setActive(true);

        priceRepository.save(newPrice);
    }
}