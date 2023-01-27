package org.bluesoft.beans;

public class Country implements Comparable<Country>{

    private String capital;
    private String continent;
    private String language;

    private long population;

    public Country(final String capital, final String continent, final String language, final long population) {
        this.capital = capital;
        this.continent = continent;
        this.language = language;
        this.population = population;
    }

    public Country() {
    }

    @Override
    public int compareTo(final Country o) {
        return 1;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(final String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(final String continent) {
        this.continent = continent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(final long population) {
        this.population = population;
    }
}
