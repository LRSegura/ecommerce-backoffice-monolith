package com.code2ever.backoffice.application.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DtoChangeProductPrice(
        @NotNull
        @Positive
        BigDecimal newPrice,

        String reason) {
}
