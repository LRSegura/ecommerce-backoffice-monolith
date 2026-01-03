package persistence.jpa.catalog;

import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.catalog.category.port.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import persistence.jpa.common.BaseJpaRepository;

import java.util.Optional;

@ApplicationScoped
public class JpaCategoryRepository extends BaseJpaRepository<Category> implements CategoryRepository {
    @Override
    public Optional<Category> findByNameIgnoreCase(String name) {
        return em.createQuery(
                        "SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name)",
                        Category.class
                ).setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }
}
