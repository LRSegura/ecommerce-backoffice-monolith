package com.code2ever.backoffice.domain.catalog.model;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DISCONTINUED("Discontinued");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }

}
