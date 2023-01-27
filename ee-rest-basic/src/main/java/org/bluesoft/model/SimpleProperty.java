package org.bluesoft.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleProperty {

    private String key;
    private String value;

    public SimpleProperty() {
    }

    public SimpleProperty(final String key, final String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
