package org.bluesoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;
    private String address;
    private String email;

}
