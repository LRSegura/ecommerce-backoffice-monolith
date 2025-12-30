package com.code2ever.backoffice.domain.order;

import com.code2ever.backoffice.domain.common.BaseEntity;
import com.code2ever.backoffice.domain.customer.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "entity_seq", sequenceName = "order_seq")
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
public class Order extends BaseEntity {

    @NotNull(message = "Order number cannot be null")
    @Column(unique = true, nullable = false)
    private String orderNumber;

    @NotNull(message = "Order status cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull(message = "Order total amount cannot be null")
    @Column(nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_order_customer"))
    private Customer customer;

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", customer=" + customer +
                '}' + super.toString();
    }
}
