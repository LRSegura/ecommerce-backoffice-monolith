package com.code2ever.backoffice.domain.customer;

import lombok.Getter;

@Getter
public enum CustomerStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String description;

    CustomerStatus(String description) {
        this.description = description;
    }
}
