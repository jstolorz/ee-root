package org.bluesoft.bean;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.bluesoft.model.Word;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class WordInventory {

    private List<Word> wordList = new ArrayList<>();

    public void addWord(@Observes Word word){
        System.out.println("Added new anagram " + word.getAnagram());
        wordList.add(word);
    }
}
