package com.thoughtworks.account;

import java.util.Date;

public class CreditTransaction extends Transaction {
    protected CreditTransaction(Date date , String name , double amount,double availableBalance) {
        super(date, name, amount , availableBalance );
    }
    public CreditTransaction(double amount, String name,double availableBalance) {
        this(new Date(),name,amount,availableBalance);
    }
    public boolean isCreditTransaction() {
        return true;
    }

    @Override
    public boolean isDebitTransaction() {
        return false;
    }
}
