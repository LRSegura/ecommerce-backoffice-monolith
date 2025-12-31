package com.code2ever.backoffice.domain.order;

import com.code2ever.backoffice.domain.common.BaseEntity;
import com.code2ever.backoffice.domain.catalog.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "order_item_id"))
public class OrderItem extends BaseEntity {

    @NotNull(message = "Order item unit price cannot be null")
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @NotNull(message = "Order item quantity cannot be null")
    @Column(nullable = false)
    private Integer quantity;

    @Column
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_item_order"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_order_item_product"))
    private Product product;

//    @PrePersist
//    @PreUpdate
//    private void calculateTotal() {
//        this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
//    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}' + super.toString();
    }


}
