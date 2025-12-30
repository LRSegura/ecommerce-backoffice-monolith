package com.code2ever.backoffice.domain.catalog;

import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "entity_seq", sequenceName = "brand_seq")
@AttributeOverride(name = "id", column = @Column(name = "brand_id"))
public class Brand extends BaseEntity {

    @NotNull(message = "Brand name cannot be null")
    @Column(unique = true, nullable = false)
    private String name;

    @Column()
    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}' + super.toString();
    }
}
