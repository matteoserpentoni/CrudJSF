package com.matteoserpentoni.crudjsf.business;

import java.time.LocalDate;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.matteoserpentoni.crudjsf.model.Customer;

@Startup
@Singleton
public class InitialCustomerFiller {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {

        System.out.println("Storing three initial customers");

        this.entityManager.persist(createCustomer("Matte", "Serpe", "serpe@gmail.com", LocalDate.of(1995, 12, 30)));
        this.entityManager.persist(createCustomer("Ale", "Falza", "bello.falza@gmail.com", LocalDate.of(1995, 1,
                25)));

    }

    private Customer createCustomer(String firstName, String lastName, String email, LocalDate dayOfBirth) {
        Customer result = new Customer();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setEmail(email);
        result.setDayOfBirth(dayOfBirth);
        result.setCustomerId(UUID.randomUUID().toString().substring(0, 8));
        return result;
    }
}