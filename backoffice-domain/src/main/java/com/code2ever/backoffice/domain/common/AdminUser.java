package com.code2ever.backoffice.domain.common;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "entity_seq", sequenceName = "admin_user_seq")
@AttributeOverride(name = "id", column = @Column(name = "admin_user_id"))
public class AdminUser extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private Boolean active;

    @Override
    public String toString() {
        return "AdminUser{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}' + super.toString();
    }
}
