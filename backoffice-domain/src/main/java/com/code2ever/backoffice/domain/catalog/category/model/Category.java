<<<<<<<< HEAD:backoffice-domain/src/main/java/com/code2ever/backoffice/domain/catalog/category/model/Category.java
package com.code2ever.backoffice.domain.catalog.category.model;
========
package com.code2ever.backoffice.domain.catalog.model;
>>>>>>>> origin/main:backoffice-domain/src/main/java/com/code2ever/backoffice/domain/catalog/model/Category.java

import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class Category extends BaseEntity {

    @NotNull(message = "Category name cannot be null")
    @Column(unique = true, nullable = false)
    private String name;

    @Column()
    private String description;

    @Column()
    private Boolean active;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}' + super.toString();
    }
}
