package org.bluesoft.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.*;
import org.bluesoft.model.Customer;
import org.bluesoft.model.CustomerDTO;
import org.bluesoft.model.CustomerDTOMapper;
import org.bluesoft.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Path("customers")
@Controller
@RequestScoped
public class CustomerController {

    @Inject
    private CustomerDTOMapper mapper;

    @Inject
    private Models models;

    @Inject
    private CustomerRepository repository;


    @Path("edit/{id}")
    @GET
    public String preEdit(@PathParam("id")Long id){
        Customer customer = repository.findCustomerById(id);
        models.put("customer",customer);
        return "edit.xhtml";
    }


    @GET
    public String liatCustomers(){
        List<CustomerDTO> list = getCustomerDTOList();

        models.put("customers",list);
        System.out.println("Called form!");
        return "list.xhtml";
    }

    @Path("new")
    @Controller
    @GET
    public String preAdd(){
        return "insert.xhtml";
    }

    @Path("new")
    @POST
    public String createCustomer(final @BeanParam Customer customer){
        repository.createCustomer(customer);
        List<CustomerDTO> list = getCustomerDTOList();
        models.put("customers",list);
        return "list.xhtml";
    }

    @Path("edit/{id}")
    @POST
    public String edit(final @PathParam("id") Long id, final @BeanParam Customer customer){
       customer.setId(id);
       repository.updateCustomer(customer);
       List<CustomerDTO> list = getCustomerDTOList();
       models.put("customers",list);
       return "list.xhtml";
    }

    @Path("delete/{id}")
    @GET
    public String delete(final @PathParam("id") Long id){
        repository.deleteCustomer(id);
        List<CustomerDTO> list = getCustomerDTOList();
        models.put("customers",list);
        return "list.xhtml";
    }

    // DTO pattern example
    private List<CustomerDTO> getCustomerDTOList() {
        List<CustomerDTO> list = repository.findAllCustomers()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
        return list;
    }


}
