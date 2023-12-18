package com.example.classicmodelonline.Test;

import com.example.classicmodelonline.entities.Employee;
import com.example.classicmodelonline.entities.Environment;
import com.example.classicmodelonline.entities.Office;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class TestEntityRelationship {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.PU_NAME);
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("Find employee by office code (enter 0 to exit) : ");
            String officeCode = sc.next();
            if(officeCode.equalsIgnoreCase("0")){
                break;
            }
            Office office = em.find(Office.class , officeCode);
            if(office==null){
                System.out.println("Office number " + officeCode + " does not exit");
            } else {
                displayOfficeEmployee(office);
            }
        }
    }

    private static void displayOfficeEmployee(Office o) {
        System.out.printf("%-2s %-25s %-14s %s\n",
                o.getOfficeCode(), o.getAddressLine1(),
                o.getCity(), o.getCountry());
        System.out.println("-------------------------");
        for(Employee e : o.getEmployeeList()){ //can  use from Employee entity because already define relationship
            System.out.printf("%4d %-12s %-12s %s\n",
                    e.getEmployeeNumber(),e.getFirstName(),
                    e.getLastName(),e.getJobTitle());
        }
        System.out.println();
    }
}
