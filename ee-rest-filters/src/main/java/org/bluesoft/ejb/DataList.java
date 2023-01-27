package org.bluesoft.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.bluesoft.model.SimpleProperty;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class DataList {

    private List<SimpleProperty> list;

    @PostConstruct
    public void init(){
        list = new ArrayList<>();
    }

    public List<SimpleProperty> getList() {
        return list;
    }

    public void setList(final List<SimpleProperty> list) {
        this.list = list;
    }

    public int addToList(String key, String value) {

        list.add(new SimpleProperty(key,value));
        return list.size();
    }
    public int addToList(SimpleProperty p) {
        list.add(p);
        return list.size();
    }

}
