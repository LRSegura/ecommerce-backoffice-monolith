package com.code2ever.backoffice.application.product.dto;


import com.code2ever.backoffice.domain.catalog.product.model.PriceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreatePriceCommand(
        Long productId,
        BigDecimal price,
        String currency,
        PriceType type,
        LocalDateTime validFrom,
        LocalDateTime validTo
) {}
