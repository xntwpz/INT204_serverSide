package com.example.classicmodelonline.models;

import com.example.classicmodelonline.entities.Product;


public class ClassicModelLineItem implements CartItem {
    // implement all method
        private Product product;
    private int quantity;
    private double percentDiscount;

    public ClassicModelLineItem(Product product) {
        this(product, 1, 0.0);
    }

    public ClassicModelLineItem(Product product, int quantity) {
        this(product, quantity, 0.0);
    }

    public ClassicModelLineItem(Product product, int quantity, double percentDiscount) {
        this.product = product;
        this.quantity = quantity;
        this.percentDiscount = percentDiscount;
    }
    public Product getProduct(){
        return this.product;
    }
    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getUnitPrice() {
        return product.getPrice().doubleValue();
    }

    @Override
    public double getTotal() {
        return getUnitPrice() * getQuantity() -
                getUnitPrice() * getQuantity() * percentDiscount;
    }

    @Override
    public double getPercentDiscount() {
        return this.percentDiscount;
    }

    public void setPercentDiscount(double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    @Override
    public String toString() {
        return '{' + product.getProductName() + ", " +
                getUnitPrice() + ", " + quantity + ", " +
                percentDiscount + "% " + "(" + getTotal() + ") }";
        // check that u right calculate = display total()
    }
}
