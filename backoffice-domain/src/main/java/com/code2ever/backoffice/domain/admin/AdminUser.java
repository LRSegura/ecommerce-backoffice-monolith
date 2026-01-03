package com.code2ever.backoffice.domain.admin;

import com.code2ever.backoffice.domain.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "admin_user_id"))
public class AdminUser extends BaseEntity {

    @NotNull(message = "Admin user name cannot be null")
    @Column(unique = true, nullable = false)
    private String userName;

    @NotNull(message = "Admin user email cannot be null")
    @Email(message = "Invalid email address")
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
