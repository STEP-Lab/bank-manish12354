package com.thoughtworks.account;

import java.util.Date;

public class DebitTransaction extends Transaction {
    protected DebitTransaction(Date date, String to, double amount) {
        super(date, to, amount);
    }

    public DebitTransaction(double amount, String name) {
        this(new Date(),name,amount);
    }
}
