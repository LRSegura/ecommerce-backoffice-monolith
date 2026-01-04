package com.code2ever.backoffice.domain.catalog.product.model;

import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "product_price_id"))
public class ProductPrice extends BaseEntity {

    @NotNull(message = "Product price cannot be null")
    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal price;

    @Column(length = 3, nullable = false)
    private String currency = "USD";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private PriceType priceType = PriceType.LIST;

    @Column
    private LocalDateTime validFrom;

    @Column
    private LocalDateTime validTo;

    @Column
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_price_product"))
    private Product product;

    public boolean isValidAt(LocalDateTime date) {
        if (Boolean.FALSE.equals(active)) return false;
        boolean afterStart = validFrom.isBefore(date) || validFrom.equals(date);
        boolean beforeEnd = validTo == null || validTo.isAfter(date);
        return afterStart && beforeEnd;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "price=" + price +
                ", currency=" + currency +
                ", priceType=" + priceType +
                ", validFrom='" + validFrom + '\'' +
                ", validTo='" + validTo + '\'' +
                ", active=" + active +
                ", product=" + product +
                '}' + super.toString();
    }
}
