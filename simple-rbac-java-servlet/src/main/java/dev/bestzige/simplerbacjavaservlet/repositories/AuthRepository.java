package dev.bestzige.simplerbacjavaservlet.repositories;

import dev.bestzige.simplerbacjavaservlet.entities.User;
import dev.bestzige.simplerbacjavaservlet.utils.EntityManagerBuilder;
import dev.bestzige.simplerbacjavaservlet.utils.PasswordUtils;
import jakarta.persistence.EntityManager;

public class AuthRepository {
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    }

    public boolean register(User user, String password) {
        try {
            String hash = PasswordUtils.hashPassword(password);

            user.setPassword(hash);

            getEntityManager().getTransaction().begin();
            getEntityManager().persist(user);
            getEntityManager().getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String username, String password) {
        try {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.findByUsername(username);

            if (!PasswordUtils.isPasswordValid(user.getPassword(), password)) {
                throw new Exception("Invalid password");
            }

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
