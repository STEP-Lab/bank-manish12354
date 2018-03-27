package com.thoughtworks.account;

import java.util.Date;

public class DebitTransaction extends Transaction {
    protected DebitTransaction(Date date , String to , double amount,double availableBalance) {
        super(date, to, amount, availableBalance);
    }

    @Override
    public boolean isCreditTransaction() {
        return false;
    }

    @Override
    public boolean isDebitTransaction() {
        return true;
    }

    public DebitTransaction(double amount, String name,double availableBalance) {
        this(new Date(),name,amount,availableBalance);
    }
}
