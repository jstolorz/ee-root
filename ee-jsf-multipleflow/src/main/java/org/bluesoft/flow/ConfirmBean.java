package org.bluesoft.flow;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.flow.FlowScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.UUID;

@Named
@FlowScoped("confirm")
public class ConfirmBean implements Serializable {

    private String key;
    private String secretKey;

    @PostConstruct
    public void createKey(){
        secretKey = UUID.randomUUID().toString();
        System.out.println("Secret key is " + secretKey);
    }

    public String getHomeAction(){
        return "/index";
    }

    public String checkKey(){
        if(key.equals(secretKey)){
            return "confirm2";
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong key!",
                            "You have entered a wrong key! "));
            return null;
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }
}
