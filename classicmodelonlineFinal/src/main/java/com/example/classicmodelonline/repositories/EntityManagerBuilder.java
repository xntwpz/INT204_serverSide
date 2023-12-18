package com.example.classicmodelonline.repositories;

import com.example.classicmodelonline.entities.Environment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerBuilder {
    private final static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory(Environment.PU_NAME);
    public static EntityManager getEntityManager(){

        return emf.createEntityManager();
    }

}
