package org.example.exception;

public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(final String message) {
        super(message);
    }
}
