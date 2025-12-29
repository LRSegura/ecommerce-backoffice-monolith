package com.code2ever.backoffice.domain.audit;

import lombok.Getter;

@Getter
public enum AuditLogStatus {
    CREATE( "Create"),
    UPDATE( "Update"),
    DELETE( "Delete");

    private final String description;

    AuditLogStatus(String description) {
        this.description = description;
    }
}
