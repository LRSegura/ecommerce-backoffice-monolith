package com.code2ever.backoffice.domain.shipping;

import lombok.Getter;

@Getter
public enum ShipmentStatus {
    PENDING("Pending"),
    IN_TRANSIT("In Transit"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String description;

    ShipmentStatus(String description) {
        this.description = description;
    }
}
