package org.example.exception;

public class InsufficientFundsException extends Exception{

    InsufficientFundsException(final String message) {
        super(message);
    }
}
