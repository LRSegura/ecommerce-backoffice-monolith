package com.code2ever.backoffice.domain.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED( "Created"),
    SHIPPED( "Shipped"),
    PAID( "Paid"),
    CANCELLED( "Cancelled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

}
