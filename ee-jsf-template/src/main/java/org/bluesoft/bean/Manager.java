package org.bluesoft.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bluesoft.model.User;

import java.util.ArrayList;
import java.util.List;

@Model
public class Manager {

    @Inject
    private SingletonBean ejb;

    @Produces
    @Named
    private User user;

    private List<User> userList;

    @PostConstruct
    public void retrieveAllItems(){
        user = new User();
        userList = new ArrayList<>();
    }

    @Produces
    @Named
    public List<User> getUserList() {
        return ejb.getUserList();
    }

    public void save(){
        ejb.put(user);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Added user " + user.getName(),null);
        FacesContext.getCurrentInstance().addMessage(null,facesMessage);
        user = new User();
    }

    public void delete(User user){
        ejb.delete(user);
    }

    public boolean isFull(){
        return (ejb.getUserList().size() > 0);
    }




}
