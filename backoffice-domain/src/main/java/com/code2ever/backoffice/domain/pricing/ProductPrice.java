package com.code2ever.backoffice.domain.pricing;

import com.code2ever.backoffice.domain.common.BaseEntity;
import com.code2ever.backoffice.domain.catalog.Product;
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

    @Column
    private String currency;

    @Column
    private LocalDateTime validFrom;

    @Column
    private LocalDateTime validTo;

    @Column
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_price_product"))
    private Product product;

    @Override
    public String toString() {
        return "ProductPrice{" +
                "price=" + price +
                ", currency=" + currency +
                ", validFrom='" + validFrom + '\'' +
                ", validTo='" + validTo + '\'' +
                ", active=" + active +
                ", product=" + product +
                '}' + super.toString();
    }
}
