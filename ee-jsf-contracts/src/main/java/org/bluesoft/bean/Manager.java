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
    private SingletonBean singletonBean;
    private List<User> userList;
    @Produces
    @Named
    private User user;

    @PostConstruct
    public void retrieveAllItems(){
        user = new User();
        userList = new ArrayList<>();
    }

    @Produces
    @Named
    public List<User> getUserList(){
        return singletonBean.getUserList();
    }

    public void save(){
      singletonBean.put(user);

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Added user " + user.getName(),null);

        FacesContext.getCurrentInstance().addMessage(null,facesMessage);
    }

    public void delete(User user){
        singletonBean.delete(user);
    }

    public boolean isFull(){
        System.out.println("Called isFull");
        return (singletonBean.getUserList().size() > 0);
    }


}
