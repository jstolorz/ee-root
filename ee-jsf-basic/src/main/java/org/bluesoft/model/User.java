package org.bluesoft.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class User implements Serializable {

    @Size(min = 5, max = 20, message = "Please enter a valid name (5-20 characters)")
    private String name;

    @Size(min = 5, max = 20, message = "Please enter a valid surname (5-20 characters)")
    private String surname;

    @Size(min = 5, message = "Mail size is at least 5 characters")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
