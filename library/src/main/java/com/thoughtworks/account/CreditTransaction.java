package com.thoughtworks.account;

import java.util.Date;

public class CreditTransaction extends Transaction {
    protected CreditTransaction(Date date , String name,double amount) {
        super(date, name, amount);
    }
    public CreditTransaction(double amount, String name) {
        this(new Date(),name,amount);
    }
    public boolean isCreditTransaction() {
        return true;
    }

    @Override
    public boolean isDebitTransaction() {
        return false;
    }
}
