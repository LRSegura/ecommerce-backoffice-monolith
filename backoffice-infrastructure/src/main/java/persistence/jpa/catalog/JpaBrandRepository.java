package persistence.jpa.catalog;

import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.brand.port.BrandRepository;
import jakarta.enterprise.context.ApplicationScoped;
import persistence.jpa.common.BaseJpaRepository;

import java.util.Optional;

@ApplicationScoped
public class JpaBrandRepository extends BaseJpaRepository<Brand> implements BrandRepository {

    @Override
    public Optional<Brand> findByNameIgnoreCase(String name) {
        return em.createQuery(
                        "SELECT b FROM Brand b WHERE LOWER(b.name) = LOWER(:name)",
                        Brand.class
                ).setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }
}
