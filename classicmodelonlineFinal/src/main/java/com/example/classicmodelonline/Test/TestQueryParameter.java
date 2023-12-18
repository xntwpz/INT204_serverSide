package com.example.classicmodelonline.Test;

import com.example.classicmodelonline.entities.Environment;
import com.example.classicmodelonline.entities.Office;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class TestQueryParameter {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.PU_NAME);
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("OFFICE.FIND_BY_COUNTRY");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(" Serch your office by Country : ");
            String country = scanner.next();
            if (country.equalsIgnoreCase("0")) {
                break;
            }
            query.setParameter("countryParam", country + "%");
            //set parameter before getResult() from Office class
            // use "like" must use "%"
            // %% = get all , can type %
            List<Office> officeList = query.getResultList();
            for (Office o : officeList
            ) {
                System.out.printf("%-2s %-25s %-13s %s\n", o.getOfficeCode(),
                        o.getAddressLine1(), o.getCity(), o.getCountry());
            }
        }
        // use while loop for loop code , type 0 for exit
        em.close();
    }
}
