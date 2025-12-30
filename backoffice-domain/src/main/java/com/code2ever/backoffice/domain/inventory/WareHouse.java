package com.code2ever.backoffice.domain.inventory;

import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "entity_seq", sequenceName = "ware_house_seq")
@AttributeOverride(name = "id", column = @Column(name = "ware_house_id"))
public class WareHouse extends BaseEntity {

    @NotNull(message = "Ware house name cannot be null")
    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String location;

    @Column
    private String description;

    @Column
    private Boolean active;

    @Override
    public String toString() {
        return "WareHouse{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}' + super.toString();
    }
}
