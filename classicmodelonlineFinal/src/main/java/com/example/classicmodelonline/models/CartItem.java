package com.example.classicmodelonline.models;

public interface CartItem {
    // want 5 method()
    // all abstract

    public int getQuantity();
    public void setQuantity(int quantity);
    public double getUnitPrice();
    public double getTotal();
    public double getPercentDiscount();
}
