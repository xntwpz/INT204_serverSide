package com.example.classicmodelonline.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "E.FIND_ALL" , query = "select e from Employee e"),
        @NamedQuery(name = "E.FIND_BY_NAME" , query =
                "select e from Employee e where e.firstName LIKE :first_name or e.lastName LIKE :last_name")
})
public class Employee {
    @Id
    private Integer employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private String reportsTo;
    private String jobTitle;
    @ManyToOne
    @JoinColumn(name = "officeCode",insertable = false , updatable = false)
    private Office office;
}
