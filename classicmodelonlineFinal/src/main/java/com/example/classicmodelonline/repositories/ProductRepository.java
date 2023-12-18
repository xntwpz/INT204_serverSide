package com.example.classicmodelonline.repositories;

import com.example.classicmodelonline.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ProductRepository {
    private EntityManager entityManager;
    private final static int PAGE_SIZE = 10;

    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    }

    public int getDefaultPageSize() {
        return PAGE_SIZE;
    }

    // find all , have page size cause have many products ; by calculate
    public List<Product> findAll(int page, int pageSize) {
        int startPosition = (page - 1) * pageSize;
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("PRODUCT.FIND_ALL");
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<Product> productList = query.getResultList();
        // entityManager.close();
        return productList;
    }

    public Product findProduct(String productCode) {
        return  getEntityManager().find(Product.class, productCode);
    }

    public int countAll() {
        EntityManager entityManager = getEntityManager();
        int number = ((Number) entityManager.createNamedQuery("PRODUCT.COUNT").getSingleResult()).intValue();
        // entityManager.close();
        return number;
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
