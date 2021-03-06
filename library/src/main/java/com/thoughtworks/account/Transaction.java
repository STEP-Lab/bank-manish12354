package com.thoughtworks.account;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    private final double amount;
    private Double availableBalance;
    private final Date date;
    private final String to;

    public Transaction(Date date , String to , double amount , Double availableBalance) {
        this.date = date;
        this.to = to;
        this.amount = amount;
        this.availableBalance = availableBalance;
    }
    Date getDate(){
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare ( that.amount , amount ) == 0 &&
                Objects.equals ( to , that.to );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( amount , to);
    }

    public double getAmount() {
        return amount;
    }

    public abstract boolean isCreditTransaction();

    public abstract boolean isDebitTransaction();

    public Double getAvailableBalance() {
        return availableBalance;
    }
}

