package com.thoughtworks.account;


public class Account {
    private final String holderName;
    final String accountNumber;
    private final double minBalance;
    private double accountBalance;

    public Account(String holderName , String accountNumber , double accountBalance) throws LowBalanceException, InvalidAccountNumException {
        this.minBalance = 1000;
        if (accountBalance< this.minBalance) {
            throw new LowBalanceException ();
        }
        boolean match = accountNumber.matches("^\\d{4}\\-\\d{4}$");
        this.holderName = holderName;
        if(!match){
            throw new InvalidAccountNumException ();
        }
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public double getBalance() {
        return accountBalance;
    }

    public String getAccountNumber() {
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

