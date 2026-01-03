package persistence.jpa.catalog;

import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.port.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import persistence.jpa.common.BaseJpaRepository;

@ApplicationScoped
public class JpaProductRepository extends BaseJpaRepository<Product> implements ProductRepository {

    @Override
    public boolean existsBySku(String sku) {
        return false;
    }

}
