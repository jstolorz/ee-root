package org.bluesoft.flow;


import jakarta.annotation.PostConstruct;
import jakarta.faces.lifecycle.ClientWindowScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.UUID;

@Named
@ClientWindowScoped
public class SignupBean implements Serializable {

    private String uuid;

    @PostConstruct
    public void init(){
        System.out.println("############# Created SignupBean");
        uuid = UUID.randomUUID().toString();
    }

    public SignupBean() {
    }

    public String getName(){
        return this.getClass().getSimpleName();
    }

    public String getHomeAction() {
        return "/index";
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }
}
