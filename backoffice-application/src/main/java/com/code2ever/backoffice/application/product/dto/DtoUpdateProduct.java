package com.code2ever.backoffice.application.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoUpdateProduct(
        @NotBlank
        String name,

        String description,

        @NotNull
        Long brandId,

        @NotNull
        Long categoryId
) {
}
