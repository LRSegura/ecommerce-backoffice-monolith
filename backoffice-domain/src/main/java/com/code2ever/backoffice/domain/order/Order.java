package com.code2ever.backoffice.domain.order;

import com.code2ever.backoffice.domain.common.BaseEntity;
import com.code2ever.backoffice.domain.customer.Customer;
import jakarta.persistence.*;
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
    @Column
    private String orderNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
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
