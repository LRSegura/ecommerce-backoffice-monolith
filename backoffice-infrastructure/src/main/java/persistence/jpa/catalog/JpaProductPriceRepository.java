package persistence.jpa.catalog;

import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.port.ProductPriceRepository;
import com.code2ever.backoffice.domain.pricing.ProductPrice;
import jakarta.enterprise.context.ApplicationScoped;
import persistence.jpa.common.BaseJpaRepository;

import java.math.BigDecimal;

@ApplicationScoped
public class JpaProductPriceRepository extends BaseJpaRepository<ProductPrice> implements ProductPriceRepository {

    @Override
    public BigDecimal findCurrentPrice(Product product) {
        return null;
    }

    @Override
    public void setNewCurrentPrice(Product product, BigDecimal newPrice) {

    }
}
