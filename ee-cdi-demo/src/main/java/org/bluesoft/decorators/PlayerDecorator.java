package org.bluesoft.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

import java.io.Serializable;

@Decorator
class PlayerDecorator implements PlayerItf, Serializable {

    @Inject
    @Delegate
    PlayerItf player;

    @Override
    public void check() {
        System.out.println("[Decorator] User check with "+player.getGuess());
        player.check();
    }

    @Override
    public String getGuess() {
        return player.getGuess();
    }

    @Override
    public void setGuess(final String guess) {
        player.setGuess(guess.toUpperCase());
    }
}
