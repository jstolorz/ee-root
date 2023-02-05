package org.bluesoft.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.ws.rs.WebApplicationException;
import org.bluesoft.model.Customer;

import java.util.List;

@Stateless
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    public void createCustomer(final Customer customer){
        em.persist(customer);
        System.out.println("Customer created");
    }

    public void updateCustomer(final Customer customer){
        Customer customerToUpdate = findCustomerById(customer.getId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setAddress(customer.getAddress());
    }

    public void deleteCustomer(final Long id){
        Customer customer = findCustomerById(id);
        em.remove(customer);
    }

    public Customer findCustomerById(final Long id){
        Customer customer = em.find(Customer.class,id);

        if(customer == null){
            throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
        }
        return customer;
    }

    public List<Customer> findAllCustomers(){
        Query query = em.createQuery("Select c from Customer c");
        List<Customer> customerList = query.getResultList();
        return customerList;
    }

    public Customer findCustomerByName(final String name){
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.name = :name");
        query.setParameter("name",name);
        Customer customer = (Customer) query.getSingleResult();
        return customer;
    }
}
