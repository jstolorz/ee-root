package org.bluesoft.jpa.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bluesoft.jpa.ejb.ManagerEJB;
import org.bluesoft.jpa.entity.Customer;

import java.util.List;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerService {

    @Inject
    private ManagerEJB ejb;

    @POST
    public Response create(Customer customer){
        ejb.createCustomer(customer);
        return Response.status(201).build();
    }


    @GET
    public List<Customer> findAllCustomers(){
        return ejb.findAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") Long id){
        return ejb.findCustomerById(id);
    }

    @PUT
    public Response updateCustomer(Customer customer){
        ejb.updateCustomer(customer);
        return Response.status(204).build();
    }

    @DELETE
    @Path(("/{id}"))
    public Response delete(@PathParam("id") Long id){
               ejb.deleteCustomer(id);
               return Response.status(204).build();
    }

}
