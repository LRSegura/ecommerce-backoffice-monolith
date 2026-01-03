<<<<<<<< HEAD:backoffice-domain/src/main/java/com/code2ever/backoffice/domain/catalog/product/model/ProductStatus.java
package com.code2ever.backoffice.domain.catalog.product.model;
========
package com.code2ever.backoffice.domain.catalog.model;
>>>>>>>> origin/main:backoffice-domain/src/main/java/com/code2ever/backoffice/domain/catalog/model/ProductStatus.java

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
