package org.example.ejb.client;

import org.example.ejb.Account;
import org.example.ejb.Calculator;
import org.example.exception.InsufficientFundsException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class RemoteEJBClient {
    public static void main(String[] args) throws NamingException {
         Account account = lookupAccountEJB();
         Calculator calculator = lookupCalculatorEJB();

        System.out.println("Create account with 1000$");

        account.createAccount(1000l);
        System.out.println("Deposit 250$");
        account.deposit(250l);

        try {
            System.out.println("Withdraw 500$ ");
            account.withdraw(500l);
        }catch (InsufficientFundsException e){
            e.printStackTrace();
        }

        long money = account.getMoney();
        System.out.println("Money left " + money);
        float totalMoney = calculator.calculateInterest(money);
        System.out.println("Money plus interests " + totalMoney);

    }

    private static Account lookupAccountEJB() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.wildfly.naming.client.WildFlyInitialContextFactory");
        final Context context = new InitialContext(jndiProperties);

        return (Account) context
                .lookup("ejb:/ee-ejb-server-basic/AccountEJB!org.example.ejb.Account?stateful");
    }

    private static Calculator lookupCalculatorEJB() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.wildfly.naming.client.WildFlyInitialContextFactory");
        final Context context = new InitialContext(jndiProperties);

        return (Calculator) context
                .lookup("ejb:/ee-ejb-server-basic/CalculatorEJB!org.example.ejb.Calculator");
    }
}
