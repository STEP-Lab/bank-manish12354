package com.thoughtworks.bank;

public class Account {
    private final String holderName;
    private final long accountNumber;
    private double accountBalance;

    public Account(String holderName , long accountNumber, double accountBalance) throws LowBalanceException {
        if (accountBalance<1000) {
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

    public double debit(double balance) {
        if (balance < accountBalance){
            accountBalance -= balance;
        }
        return accountBalance;
    }
}
