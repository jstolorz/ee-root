package org.example.ejb;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateful;
import org.example.exception.InsufficientFundsException;

@Stateful
@Remote(Account.class)
public class AccountEJB implements Account {

    private long money;

    @Override
    public void deposit(final long amount) {
        this.money+= amount;

        System.out.println("Money deposit. total is "+money);
    }

    @Override
    public void withdraw(final long amount) throws InsufficientFundsException {
        long newAmount = money - amount;
        if (newAmount < 0) {
            throw new InsufficientFundsException("Unsufficient funds for account! ");
        }

        money = newAmount;
        System.out.println("Money withdrawal. total is "+money);
    }

    @Override
    public long getMoney() {
        return money;
    }

    @Override
    public void createAccount(final long amount) {
        this.money= amount;
    }
}
