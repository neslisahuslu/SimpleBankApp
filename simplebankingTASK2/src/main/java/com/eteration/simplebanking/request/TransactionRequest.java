package com.eteration.simplebanking.request;

import java.io.Serializable;

public class TransactionRequest implements Serializable {
    private double amount = 0.0;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
