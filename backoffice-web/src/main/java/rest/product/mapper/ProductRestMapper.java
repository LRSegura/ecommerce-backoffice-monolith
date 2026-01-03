package rest.product.mapper;

import com.code2ever.backoffice.application.product.dto.CreateProductCommand;
import com.code2ever.backoffice.application.product.dto.ProductView;
import rest.product.dto.CreateProductRequest;
import rest.product.dto.ProductResponse;

public final class ProductRestMapper {

    private ProductRestMapper() {}

    public static CreateProductCommand toCommand(CreateProductRequest req) {
        return new CreateProductCommand(
                req.sku(),
                req.name(),
                req.description(),
                req.categoryId(),
                req.brandId(),
                req.price()
        );
    }

    public static ProductResponse toResponse(ProductView view) {
        return new ProductResponse(
                view.id(),
                view.sku(),
                view.name(),
                view.brandName(),
                view.categoryName (),
                view.currentPrice()
        );
    }
}
