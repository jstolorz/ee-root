package org.bluesoft.model;

import jakarta.inject.Named;

import java.util.function.Function;

@Named
public class CustomerDTOMapper implements Function<Customer,CustomerDTO> {
    @Override
    public CustomerDTO apply(final Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getEmail()
        );
    }
}
