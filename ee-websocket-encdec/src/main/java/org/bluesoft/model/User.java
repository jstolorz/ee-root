package org.bluesoft.model;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
@Data
public class User {

    private String name;
    private String surname;
    private String email;
    private boolean registered;

}
