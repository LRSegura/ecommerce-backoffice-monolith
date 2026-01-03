package persistence.jpa.common;

import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseJpaRepository<T extends BaseEntity> {

    @PersistenceContext(unitName = "backofficePU")
    protected EntityManager em;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    protected BaseJpaRepository() {
        java.lang.reflect.Type genericSuperclass = getClass().getGenericSuperclass();

        // If this is a CDI proxy, the generic superclass might not be the ParameterizedType
        // we are looking for. We loop up until we find it.
        while (!(genericSuperclass instanceof ParameterizedType)) {
            if (!(genericSuperclass instanceof Class)) {
                throw new IllegalStateException("Unable to determine entity class for " + getClass());
            }
            genericSuperclass = ((Class<?>) genericSuperclass).getGenericSuperclass();
        }

        this.entityClass = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public List<T> findAll() {
        return em.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e",
                entityClass
        ).getResultList();
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}