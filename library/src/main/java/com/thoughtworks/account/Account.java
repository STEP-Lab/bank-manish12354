package com.thoughtworks.account;

public class Account {
    private final String holderName;
    private final long accountNumber;
    private final double minBalance;
    private double accountBalance;

    public Account(String holderName , long accountNumber , double accountBalance) throws LowBalanceException {
        this.minBalance = 1000;
        if (accountBalance< this.minBalance) {
            throw new LowBalanceException ();
        }
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public double getBalance() {
        return accountBalance;
    }

    public long getAccountNumber() {
        return accountNumber;
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
}

