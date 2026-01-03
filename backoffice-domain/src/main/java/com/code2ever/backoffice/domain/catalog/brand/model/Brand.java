<<<<<<<< HEAD:backoffice-domain/src/main/java/com/code2ever/backoffice/domain/catalog/brand/model/Brand.java
package com.code2ever.backoffice.domain.catalog.brand.model;
========
package com.code2ever.backoffice.domain.catalog.model;
>>>>>>>> origin/main:backoffice-domain/src/main/java/com/code2ever/backoffice/domain/catalog/model/Brand.java

import com.code2ever.backoffice.domain.catalog.product.model.Product;
import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "brand_id"))
public class Brand extends BaseEntity {

    @NotNull(message = "Brand name cannot be null")
    @Column(unique = true, nullable = false)
    private String name;

    @Column()
    private String description;

    @Column()
    private Boolean active;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}' + super.toString();
    }
}
