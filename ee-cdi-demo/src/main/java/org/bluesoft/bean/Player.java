package org.bluesoft.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.event.Event;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bluesoft.alternatives.Rules;
import org.bluesoft.decorators.PlayerItf;
import org.bluesoft.model.Word;
import org.bluesoft.qualifier.Anagram;

import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named
public class Player implements PlayerItf, Serializable {

    @Inject
    @Anagram
    Word word;

    @Inject
    Rules rules;

    int attempt = 1;
    int maxAttempts = 5;
    private boolean engram;
    private String guess;

    @Inject
    private Event<Word> wordEvent;

    @PostConstruct
    public void init(){
        wordEvent.fire(word);
    }

    public void check(){
        if(guess.equals(word.getSolution())){
            engram = true;
            printMessage("You guessed! Click Restart");
        }else {
            printMessage("Wrong gyess!");
        }

        if(attempt == rules.getMaxAttempts()){
            printMessage("You lost! Click Restart");
            engram = true;
        }
    }


    private void printMessage(String string){
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, string, string));
    }

    public void restart() {
        ExternalContext ec = FacesContext.getCurrentInstance()
                .getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public Word getWord() {
        return word;
    }

    public int getAttempt() {
        return attempt;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean isEngram() {
        return engram;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(final String guess) {
        this.guess = guess;
    }

    public Rules getRules() {
        return rules;
    }
}
