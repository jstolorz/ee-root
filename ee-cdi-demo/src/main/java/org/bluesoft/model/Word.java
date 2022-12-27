package org.bluesoft.model;

import java.io.Serializable;

public class Word implements Serializable {

    private String solution;
    private String anagram;

    public Word(final String solution, final String anagram) {
        this.solution = solution;
        this.anagram = anagram;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(final String solution) {
        this.solution = solution;
    }

    public String getAnagram() {
        return anagram;
    }

    public void setAnagram(final String anagram) {
        this.anagram = anagram;
    }
}
