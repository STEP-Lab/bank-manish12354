package com.thoughtworks.account;

import java.util.Date;

public class DebitTransaction {
    private final Date date;
    private final String account;
    private final double amount;

    public DebitTransaction(Date date , String account , double amount) {
        this.date = date;
        this.account = account;
        this.amount = amount;
    }
    Date getDate(){
        return date;
    }
}
