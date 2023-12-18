package com.example.classicmodelonline.Test;
import com.example.classicmodelonline.entities.Environment;
import com.example.classicmodelonline.entities.Office;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class TestQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.PU_NAME);
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("OFFICE.FIND_ALL");
        List<Office> officeList = query.getResultList();
        for (Office o : officeList) {
            System.out.printf("%-2s %-25s %-14s %s\n",
                    o.getOfficeCode(), o.getAddressLine1(), o.getCity(), o.getCountry());
        }
        em.close();
    }
}
