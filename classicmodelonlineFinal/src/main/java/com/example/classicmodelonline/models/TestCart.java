package com.example.classicmodelonline.models;


import com.example.classicmodelonline.entities.Product;
import com.example.classicmodelonline.repositories.ProductRepository;

public class TestCart {
    public static void main(String[] args) {
        // Cart<K, V extends CartItem> ; key = String
        Cart<String, ClassicModelLineItem> cart = new Cart<>();
        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.findProduct("S10_1678");
        cart.addItem(product.getProductCode(), new ClassicModelLineItem(product, 5));
        product = productRepository.findProduct("S12_3380");
        cart.addItem(product.getProductCode(), new ClassicModelLineItem(product));
        System.out.println(cart.getNoOfItem());
        System.out.println(cart.getQuantity());
        System.out.println(cart.getTotalPrice()); // all in cart
        System.out.println(cart.getAllItem());
        cart.removeItem("S10_1678"); // delete
        System.out.println(cart.getAllItem());

//        2
//        6
//        595.94
//        [{1968 Dodge Charger, 117.44, 1, 0.0% }, {1969 Harley Davidson Ultimate Chopper, 95.7, 5, 0.0% }]
//        [{1968 Dodge Charger, 117.44, 1, 0.0% }]
    }
}
