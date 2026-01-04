package com.code2ever.backoffice.domain.catalog.product.model;

import lombok.Getter;

@Getter
public enum PriceType {
    LIST( "List Price"),
    SALE( "Sale Price"),
    COST( "Cost Price");

    private final String description;

    PriceType(String description){
        this.description = description;
    }
}
