package com.code2ever.backoffice.domain.customer;

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
@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
public class Customer extends BaseEntity {

    @NotNull(message = "Customer first name cannot be null")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Customer last name cannot be null")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Customer email cannot be null")
    @Email(message = "Invalid email address")
    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private CustomerStatus status = CustomerStatus.ACTIVE;

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                '}' + super.toString();
    }
}
