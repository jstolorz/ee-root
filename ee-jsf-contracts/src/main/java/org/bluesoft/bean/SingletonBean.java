package org.bluesoft.bean;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import org.bluesoft.model.User;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class SingletonBean {

    private List<User> userList;

    @PostConstruct
    public void init(){
        userList = new ArrayList<>();
    }

    public void put(User user){
        userList.add(user);
    }

    public void delete(User user){
        userList.remove(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(final List<User> userList) {
        this.userList = userList;
    }
}
