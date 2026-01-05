package persistence.jpa.catalog;

import com.code2ever.backoffice.domain.catalog.product.model.PriceType;
import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.catalog.product.port.ProductPriceRepository;
import com.code2ever.backoffice.domain.catalog.product.model.ProductPrice;
import jakarta.enterprise.context.ApplicationScoped;
import persistence.jpa.common.BaseJpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaProductPriceRepository extends BaseJpaRepository<ProductPrice> implements ProductPriceRepository {

    @Override
    public Optional<ProductPrice> findActivePrice(Long productId, PriceType type, LocalDateTime atDate) {
        String jpql = """
            SELECT p FROM ProductPrice p\s
            WHERE p.product.id = :pid\s
            AND p.priceType = :ptype\s
            AND p.active = true\s
            AND p.validFrom <= :date\s
            AND (p.validTo IS NULL OR p.validTo > :date)
            ORDER BY p.validFrom DESC
       \s""";
        return em.createQuery(jpql, ProductPrice.class)
                .setParameter("pid", productId)
                .setParameter("ptype", type)
                .setParameter("date", atDate)
                .setMaxResults(1)
                .getResultStream().findFirst();
    }

    @Override
    public List<ProductPrice> findByProductAndType(Long productId, PriceType type) {
        return em.createQuery("SELECT p FROM ProductPrice p WHERE p.product.id = :pid AND p.priceType = :ptype ORDER BY p.validFrom DESC", ProductPrice.class)
                .setParameter("pid", productId)
                .setParameter("ptype", type)
                .getResultList();
    }

    @Override
    public List<ProductPrice> findOverlappingPrices(Long productId, PriceType type, LocalDateTime from, LocalDateTime to) {
        // Lógica compleja simplificada: Buscar cualquier precio que empiece antes de que termine el nuevo y termine después de que empiece el nuevo
        // Si 'to' es null (indefinido), la lógica cambia ligeramente.
        String jpql = """
            SELECT p FROM ProductPrice p
            WHERE p.product.id = :pid
            AND p.priceType = :ptype
            AND p.active = true
            AND (
                (CAST(:end AS timestamp) IS NULL AND (p.validTo IS NULL OR p.validTo > CAST(:start AS timestamp)))
                OR
                (CAST(:end AS timestamp) IS NOT NULL AND p.validFrom < CAST(:end AS timestamp) AND (p.validTo IS NULL OR p.validTo > CAST(:start AS timestamp)))
            )
        """;
        return em.createQuery(jpql, ProductPrice.class)
                .setParameter("pid", productId)
                .setParameter("ptype", type)
                .setParameter("start", from)
                .setParameter("end", to)
                .getResultList();
    }

    @Override
    public BigDecimal findCurrentPriceValue(Product product) {
        return findActivePrice(product.getId(), PriceType.LIST, LocalDateTime.now())
                .map(ProductPrice::getPrice)
                .orElse(BigDecimal.ZERO);
    }
}
