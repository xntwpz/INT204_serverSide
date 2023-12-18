package com.example.classicmodelonline.repositories;

import com.example.classicmodelonline.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CustomerRepository {
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    } // should be public

    public Customer findByName(String name) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("CUSTOMER.FIND_USER");
        query.setParameter("user_account", name); // parameter
        // add try catch when have error
        try{
            return (Customer) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
        // return (Customer) query.getSingleResult();
    }

    public List<Customer> findAll() {
        return getEntityManager().createQuery("select c from Customer c").getResultList();
    }

}
