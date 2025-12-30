package com.code2ever.backoffice.domain.inventory;

import com.code2ever.backoffice.domain.common.BaseEntity;
import com.code2ever.backoffice.domain.catalog.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "entity_seq", sequenceName = "product_stock_seq")
@AttributeOverride(name = "id", column = @Column(name = "product_stock_id"))
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"product_id", "warehouse_id"}
))
public class ProductStock extends BaseEntity {

    @NotNull(message = "Product stock quantity cannot be null")
    @Column(nullable = false)
    private Long quantityAvailable = 0L;

    @NotNull(message = "Product stock reserved quantity cannot be null")
    @Column(nullable = false)
    private Long reservedQuantity = 0L;

    @Column
    private Long minimumStock;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_stock_product"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", foreignKey = @ForeignKey(name = "fk_product_stock_warehouse"))
    private WareHouse warehouse;

    @Override
    public String toString() {
        return "ProductStock{" +
                "quantityAvailable=" + quantityAvailable +
                ", reservedQuantity=" + reservedQuantity +
                ", minimumStock=" + minimumStock +
                ", product=" + product +
                ", warehouse=" + warehouse +
                '}' + super.toString();
    }
}
