package com.thoughtworks.account;


import java.util.Date;

public class Account {
    private AccountNumber accountNumber;
    private final String holderName;
    private final double minBalance;
    private double accountBalance;

    public Account(String holderName , AccountNumber accountNumber , double accountBalance) throws LowBalanceException, InvalidAccountNumException {
        this.minBalance = 1000;
        validateMinBalance(accountBalance,"balance can't be less than 1000");
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    private void validateMinBalance(double balance,String message) throws LowBalanceException {
        if (balance< minBalance) {
            throw new LowBalanceException (message);
        }
    }

    public double getBalance() {
        return accountBalance;
    }

    public String getHolderName() {
        return holderName;
    }

    public double credit(double balance) {
        accountBalance+=balance;
        return accountBalance;
    }

    public double debit(double balance) throws InsufficientBalanceExceptoin {
        int finalBalance = (int) (accountBalance - balance);
        if (finalBalance < minBalance){
            throw new InsufficientBalanceExceptoin();
        }
        accountBalance-= balance;
        return accountBalance;
    }
    public String getSummary(){
        Summary summary = new Summary(holderName,accountNumber,accountBalance);
        return summary.toString();
    }

    public abstract static class Transaction { 
        private final String account;
        private final Date date;
        private final double amount;
        public Transaction(Date date , String to , double amount) { 
            this.date = date;
            this.account = to;
            this.amount = amount; 
        }
        public Date getDate() {
            return date;
        }
    }
}

