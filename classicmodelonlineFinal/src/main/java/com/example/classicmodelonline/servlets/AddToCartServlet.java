package com.example.classicmodelonline.servlets;

import java.io.IOException;

import com.example.classicmodelonline.entities.Product;
import com.example.classicmodelonline.models.Cart;
import com.example.classicmodelonline.models.ClassicModelLineItem;
import com.example.classicmodelonline.repositories.ProductRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "AddToCartServlet", value = "/add-to-cart") // match jsp
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        // read productCode , then create session = request many time
        Cart<String, ClassicModelLineItem> cart = (Cart) session.getAttribute("cart");
        // handle that have cart yet ?
        if (cart == null) {
            // if not , put cart in session
            cart = new Cart<>();
            session.setAttribute("cart", cart);
        }
        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.findProduct(productCode);
        if (product != null) {
            // add item
            cart.addItem(productCode, new ClassicModelLineItem(product));
        }
        response.getWriter().print(cart.getNoOfItem()); // can be other get...
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}