package rest.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductRequest(@NotBlank @Size(max = 50)String sku,
                                   @NotBlank @Size(max = 100)String name,
                                   @NotBlank @Size(max = 250)   String description,
                                   @NotNull Long categoryId,
                                   @NotNull Long brandId,
                                   @NotNull @DecimalMin("0.00") BigDecimal price) {
}
