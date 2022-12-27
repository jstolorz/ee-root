package org.example.ejb.async;

import org.example.exception.InsufficientFundsException;

import java.util.concurrent.Future;

public interface AccountAsync {
    public void deposit(long amount);
    public void withdraw(long amount) throws InsufficientFundsException;

    public long getMoney();
    public Future<String> createAccount(long amount);
}
