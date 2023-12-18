package dev.bestzige.simplerbacjavaservlet.utils;

import dev.bestzige.simplerbacjavaservlet.Env;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerBuilder {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Env.UNIT_NAME);

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
