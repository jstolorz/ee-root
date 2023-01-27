package org.bluesoft.flow;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.flow.FlowScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@FlowScoped("signup")
public class SignupBean implements Serializable {

    private boolean licenseAccepted;

    public SignupBean() {
    }

    public String getName(){
        return this.getClass().getSimpleName();
    }

    public String getHomeAction(){
        return "/index";
    }

    public boolean isLicenseAccepted() {
        return licenseAccepted;
    }

    public void setLicenseAccepted(final boolean licenseAccepted) {
        this.licenseAccepted = licenseAccepted;
    }

    public String accept(){

        if(licenseAccepted){
            return "signup3";
        }else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "You have to read and accept the license!",
                            "You have to read and accept the license!"));
            return null;
        }

    }


}
