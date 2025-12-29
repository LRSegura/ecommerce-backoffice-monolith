package com.code2ever.backoffice.domain.shipping;

import com.code2ever.backoffice.domain.common.BaseEntity;
import com.code2ever.backoffice.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "entity_seq", sequenceName = "shipment_seq")
@AttributeOverride(name = "id", column = @Column(name = "shipment_id"))
public class Shipment extends BaseEntity {

    @Column
    private String trackingNumber;
    @Column
    private String carrier;

    @Column
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status = ShipmentStatus.PENDING;

    @Column
    private OffsetDateTime shippedAt;
    @Column
    private OffsetDateTime deliveredAt;

    @OneToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_shipment_order"))
    private Order order;

    @Override
    public String toString() {
        return "Shipment{" +
                "trackingNumber='" + trackingNumber + '\'' +
                ", carrier='" + carrier + '\'' +
                ", status=" + status +
                ", shippedAt=" + shippedAt +
                ", deliveredAt=" + deliveredAt +
                ", order=" + order +
                '}' + super.toString();
    }

}
