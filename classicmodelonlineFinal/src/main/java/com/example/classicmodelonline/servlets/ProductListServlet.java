package com.example.classicmodelonline.servlets;


import com.example.classicmodelonline.entities.Product;
import com.example.classicmodelonline.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListServlet", value = "/product-list")
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // raed parameter from request
        ProductRepository productRepository = new ProductRepository();
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        int page = pageParam == null ? 1 : Integer.valueOf(pageParam);
        // default = 10 ; in repository
        int pageSize = pageSizeParam == null ? productRepository.getDefaultPageSize() :
                Integer.valueOf(pageSizeParam); // retrieve model to work
        int itemCount = productRepository.countAll(); // retrieve number of page for sent to jsp
        int totalPage = itemCount / pageSize + (itemCount % pageSize == 0 ? 0 : 1); // calculate total page

        // check scope
        List<Product> productList = productRepository.findAll(page, pageSize); // use repository to retrieve data , sent to js
        // set attributes , must have same name with jsp
        request.setAttribute("products", productList);
        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPage", totalPage);
        // sent to jsp ro display
        getServletContext().getRequestDispatcher("/product-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // not ues now
    }
}