package com.example.classicmodelonline.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "PRODUCT.FIND_ALL", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "PRODUCT.COUNT", query = "SELECT count(p) as count FROM Product p")})

public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock; // for check null
    private Double buyPrice; // like cost , office buy cost
    // MSRP Manufacturer Suggestion Retail Price
    // must have price
    @Column(name = "MSRP")
    private Double price; // office sell price
}
