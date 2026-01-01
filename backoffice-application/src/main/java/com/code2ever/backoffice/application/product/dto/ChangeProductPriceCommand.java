package com.code2ever.backoffice.application.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ChangeProductPriceCommand(
        @NotNull
        @Positive
        BigDecimal newPrice,

        String reason) {
}
