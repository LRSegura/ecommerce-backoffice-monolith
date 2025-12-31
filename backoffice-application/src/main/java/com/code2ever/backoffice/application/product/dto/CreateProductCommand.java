package com.code2ever.backoffice.application.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductCommand(
        @NotBlank
        String sku,

        @NotBlank
        String name,

        String description,

        @NotNull
        Long brandId,

        @NotNull
        Long categoryId,

        @NotNull
        @Positive
        BigDecimal initialPrice
) {
}
