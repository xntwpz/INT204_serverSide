package com.example.classicmodelonline.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "CUSTOMER.FIND_USER",
                query = "SELECT c FROM Customer c WHERE concat(trim(c.contactFirstName), ' ', trim(c.contactLastName)) = :user_account")
})
public class Customer {
    @Id
    private Integer customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Integer salesRepEmployeeNumber;
    private Double creditLimit;
    private String password;

//    @Column(name = "contactLastName", nullable = false, length = 50)
//    private String contactLastName;
//    @Column(name = "contactFirstName", nullable = false, length = 50)
//    private String contactFirstName;
//    @Column(name = "password", length = 256)
//    private String password;
}
