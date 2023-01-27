package org.bluesoft.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class TemplateBean implements Serializable {

    private String contract = "black";

    public String getContract() {
        return contract;
    }

    public void setContract(final String contract) {
        this.contract = contract;
    }

    public String change(String s){
        System.out.println("Change to : " + s);
        this.contract = s;
        return "index";
    }
}
