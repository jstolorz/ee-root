package org.bluesoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Entity
@Data
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FormParam("name")
    @NotBlank(message = "Name is empty")
    @Column
    private String name;

    @FormParam("address")
    @NotBlank(message = "Address is empty")
    @Size(min = 10, max = 200)
    @Column
    private String address;

    @FormParam("email")
    @NotBlank(message = "Email is empty")
    @Column
    private String email;

}
