package org.bluesoft.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    public String product;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(final String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", customer=" + customer +
                ", product='" + product + '\'' +
                '}';
    }
}
