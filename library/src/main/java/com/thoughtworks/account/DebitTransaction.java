package com.thoughtworks.account;

import java.util.Date;

public class DebitTransaction extends Transaction {
    protected DebitTransaction(Date date, String to, double amount) {
        super(date, to, amount);
    }

    @Override
    public boolean isCreditTransaction() {
        return false;
    }

    @Override
    public boolean isDebitTransaction() {
        return true;
    }

    public DebitTransaction(double amount, String name) {
        this(new Date(),name,amount);
    }
}
