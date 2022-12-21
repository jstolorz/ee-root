package org.example.ejb.client;

import org.example.ejb.Calculator;
import org.example.ejb.CalculatorEJB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class RemoteEJBClient {
    public static void main(String[] args) throws NamingException {
        Calculator calculator = lookupCalculatorEJB();
        long money = 34566;
        float totalMoney = calculator.calculateInterest(money);
        System.out.println("Money plus interests " + totalMoney);

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
