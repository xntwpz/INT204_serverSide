package com.example.classicmodelonline.Test;

import com.example.classicmodelonline.entities.Environment;
import com.example.classicmodelonline.entities.Office;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class TestJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.PU_NAME);
        EntityManager em = emf.createEntityManager();
        System.out.print("Find office by office code: ");
        String x = new Scanner(System.in).next();
        Office office = em.find(Office.class,x);
        if (office == null){
            System.out.println("Office no " + x + " does not exits");
            Office newOffice = new Office();
            newOffice.setOfficeCode(x);
            newOffice.setCity("Bangkok");
            newOffice.setPhone("0925487782");
            newOffice.setCountry("Thailand");
            newOffice.setState("Asia");
            newOffice.setTerritory("TH");
            newOffice.setAddressLine1("26/2 M8 Banglen");
            newOffice.setPostalCode("12365");
            em.getTransaction().begin();
            em.persist(newOffice); //add
            em.getTransaction().commit(); //save
        } else {
            System.out.println(office);
            System.out.println("Do you want to delete office no "+ x + " (Y?N) ? ");
            String answer = new Scanner(System.in).next();
            if(answer.equalsIgnoreCase("Y")){
                em.getTransaction().begin();
                em.remove(office); //remove
                em.getTransaction().commit();
                System.out.println("Office no " + x + " has been removed !!! ");
            }
        }

        emf.close();
        em.close();
    }
}
