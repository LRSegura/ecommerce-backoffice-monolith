package com.code2ever.backoffice.domain.catalog.product.model;


import com.code2ever.backoffice.domain.catalog.brand.model.Brand;
import com.code2ever.backoffice.domain.catalog.category.model.Category;
import com.code2ever.backoffice.domain.common.BaseEntity;
import com.code2ever.backoffice.domain.inventory.ProductStock;
import com.code2ever.backoffice.domain.pricing.ProductPrice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "product_id"))
public class Product extends BaseEntity {

    @NotNull(message = "Product SKU cannot be null")
    @Column(unique = true, nullable = false)
    private String sku;

    @NotNull(message = "Product name cannot be null")
    @Column(unique = true, nullable = false)
    private String name;

    @Column()
    private String description;

    @Column()
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "fk_product_brand"))
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPrice> productPrices;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductStock> productStocks;

    public boolean isActive() {
        return status == ProductStatus.ACTIVE;
    }

    public boolean isInactive() {
        return status == ProductStatus.INACTIVE;
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", brand=" + brand +
                ", category=" + category +
                '}' + super.toString();
    }
}
