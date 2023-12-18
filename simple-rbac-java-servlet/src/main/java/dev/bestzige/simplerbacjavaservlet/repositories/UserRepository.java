package dev.bestzige.simplerbacjavaservlet.repositories;

import dev.bestzige.simplerbacjavaservlet.entities.User;
import dev.bestzige.simplerbacjavaservlet.utils.EntityManagerBuilder;
import dev.bestzige.simplerbacjavaservlet.utils.StringUtils;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRepository {
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    }

    public User findByUsername(String username) {
        try {
            return getEntityManager().createNamedQuery("User.findByUsername", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findById(int id) {
        try {
            return getEntityManager().find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int countAll(String search) {
        try {
            if(StringUtils.isNullOrEmpty(search)) {
                return getEntityManager().createNamedQuery("User.countAll", Long.class)
                        .getSingleResult()
                        .intValue();
            }

            return getEntityManager().createNamedQuery("User.countAllWithSearch", Long.class)
                    .setParameter("search", "%" + search + "%")
                    .getSingleResult()
                    .intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<User> findAll(int page, int limit, String search) {
        try {
            limit = limit > 0 && limit <= 100 ? limit : 10;
            page = page > 0 ? page : 1;

            if(StringUtils.isNullOrEmpty(search)) {
                return getEntityManager().createNamedQuery("User.findAll", User.class)
                        .setFirstResult((page - 1) * limit)
                        .setMaxResults(limit)
                        .getResultList();
            }

            return getEntityManager().createNamedQuery("User.findAllWithSearch", User.class)
                    .setParameter("search", "%" + search + "%")
                    .setFirstResult((page - 1) * limit)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean remove(User user) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(user);
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(User user) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(user);
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
