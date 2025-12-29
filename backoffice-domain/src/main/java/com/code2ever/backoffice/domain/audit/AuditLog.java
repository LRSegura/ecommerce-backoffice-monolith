package com.code2ever.backoffice.domain.audit;

import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "entity_seq", sequenceName = "audit_log_seq")
@AttributeOverride(name = "id", column = @Column(name = "audit_log_id"))
public class AuditLog extends BaseEntity {
    @Column
    private String entityName;

    @Column
    private Long entityId;

    @Lob
    @Column
    private String details;

    @Column
    @Enumerated(EnumType.STRING)
    private AuditLogStatus auditLogAction;

    @Column
    private String performedBy;

    @Column
    private Instant performedAt;

    @Override
    public String toString() {
        return "AuditLog{" +
                "entityName='" + entityName + '\'' +
                ", entityId=" + entityId +
                ", details='" + details + '\'' +
                ", auditLogAction=" + auditLogAction +
                ", performedBy='" + performedBy + '\'' +
                ", performedAt=" + performedAt +
                '}' + super.toString();
    }
}
