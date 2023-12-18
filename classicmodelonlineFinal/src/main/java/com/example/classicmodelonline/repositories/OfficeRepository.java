package com.example.classicmodelonline.repositories;

import com.example.classicmodelonline.entities.Office;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class OfficeRepository {
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    }

    public List<Office> findAll(){
        return getEntityManager().createNamedQuery("OFFICE.FIND_ALL").getResultList();
    } // this is named query

    public List<Office> findByCityOrCountry(String cityOrCountry) {
        cityOrCountry = cityOrCountry.toLowerCase()+'%';
        Query query = getEntityManager().createNamedQuery("Office.FIND_BY_CITY_OR_COUNTRY");
        query.setParameter("city", cityOrCountry);
        query.setParameter("country", cityOrCountry);
        return query.getResultList();
    }
    public Office find(String officeCode) {
        return getEntityManager().find(Office.class, officeCode);
    }

    public boolean insert(Office office) {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(office);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // delete by officeCode
    public boolean delete(String officeCode) {
        EntityManager entityManager = getEntityManager();
        Office office = find(officeCode);
        if (office != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(office);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    // delete office
    public boolean delete(Office office) {
        if (office != null) {
            EntityManager entityManager = getEntityManager();
            if (entityManager.contains(office)) {
                entityManager.getTransaction().begin();
                entityManager.remove(office);
                entityManager.getTransaction().commit();
                return true;
            } else {
                return delete(office.getOfficeCode());
            }
        }
        return false;
    }

    public boolean update(Office newOffice) {
        if (newOffice != null) {
            EntityManager entityManager = getEntityManager();
            Office office = find(newOffice.getOfficeCode());
            if (office != null) {
                entityManager.getTransaction().begin();
                //set all attributes office with newOffice (DIY)
                entityManager.merge(newOffice);
                // ..
                entityManager.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    public static boolean isValidString(String s) {
        if(s==null || s.trim().isEmpty()){
            return false;
        }
        return true;
    }

    public Office newOffice(HttpServletRequest request, HttpServletResponse response){
        Office office = new Office();
        office.setOfficeCode(isValidString(request.getParameter("officeCode"))?request.getParameter("officeCode"):null);
        office.setCity(request.getParameter("city"));
        office.setState(request.getParameter("state"));
        office.setTerritory(request.getParameter("territory"));
        office.setPostalCode(request.getParameter("postalCode"));
        office.setCountry(request.getParameter("country"));
        office.setPhone(request.getParameter("phone"));
        office.setAddressLine1(request.getParameter("addressLine1"));
        office.setAddressLine2(request.getParameter("addressLine2"));
        return office;
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }


}
