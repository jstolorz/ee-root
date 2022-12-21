package org.example.ejb;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@Stateless
@Remote(Calculator.class)
public class CalculatorEJB implements Calculator {

    private float interest = 5;


    @Override
    public float calculateInterest(final long money) {
        return money * (1 + (interest/100));
    }
}
