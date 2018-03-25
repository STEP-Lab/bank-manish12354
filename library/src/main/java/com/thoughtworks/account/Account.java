package com.thoughtworks.account;


import java.util.Date;

public class Account {
    private AccountNumber accountNumber;
    private final String holderName;
    private final double minBalance;
    private double accountBalance;
    public Transactions transactions;

    public Account(String holderName , AccountNumber accountNumber , double accountBalance) throws LowBalanceException, InvalidAccountNumException {
        this.minBalance = 1000;
        validateMinBalance(accountBalance,"balance can't be less than 1000");
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.transactions = new Transactions ();
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

    public double credit(double amount) {
        this.transactions.credit(amount,this.getHolderName ());
        accountBalance+=amount;
        return accountBalance;
    }

    public double debit(double amount) throws InsufficientBalanceExceptoin {
        int finalBalance = (int) (accountBalance - amount);
        if (finalBalance < minBalance){
            throw new InsufficientBalanceExceptoin();
        }
        this.transactions.debit ( amount,this.getHolderName ());
        accountBalance-= amount;
        return accountBalance;
    }
    public String getSummary(){
        Summary summary = new Summary(holderName,accountNumber,accountBalance);
        return summary.toString();
    }
}

