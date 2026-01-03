package rest.product.dto;

import java.math.BigDecimal;

public record ProductResponse(Long id,
                              String sku,
                              String name,
                              String brandName,
                              String categoryName,
                              BigDecimal price) {
}
