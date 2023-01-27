package org.bluesoft.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Named
@ViewScoped
public class CountryBean implements Serializable {

    private HashMap<String,Country> countries = new HashMap<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public CountryBean() {
        countries.put("Poland",new Country("Warsaw","Europe","Polish",38538447));
        countries.put("Italy", new Country("Rome","Europe","Italian",65000000));
        countries.put("England", new Country("London","Europe","Italian",60000000));
        countries.put("United States", new Country("Washington","America","Italian",325000000));
        countries.put("Germany", new Country("Berlin","Europe","German",85000000));
    }

    public HashMap<String, Country> getCountries() {
        return countries;
    }
}
