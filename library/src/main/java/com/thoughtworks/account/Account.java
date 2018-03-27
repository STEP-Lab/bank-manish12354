package com.thoughtworks.account;

import java.util.ArrayList;

public class Account {
    private AccountNumber accountNumber;
    private final String holderName;
    private static final double minBalance = 1000;
    private double accountBalance;
    protected Transactions transactions;

    public Account(String holderName , AccountNumber accountNumber , double accountBalance) throws LowBalanceException, InvalidAccountNumException {
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.transactions = new Transactions ();
    }

    public static Account CreateAcc(String holderName , AccountNumber accountNumber , double accountBalance) throws LowBalanceException, InvalidAccountNumException {
        validateBalance(accountBalance);
        return new Account ( holderName , accountNumber , accountBalance );
    }

    private static void validateBalance(double accountBalance) throws LowBalanceException {
        if (accountBalance < minBalance) {
            throw new LowBalanceException ( "balance can't be less than 1000" );
        }
    }

    public double getBalance() {
        return accountBalance;
    }

    public String getHolderName() {
        return holderName;
    }

    public double credit(double amount) throws InvalidAmountException {
        validateAmount(amount);
        this.transactions.credit ( amount , this.getHolderName () );
        accountBalance += amount;
        return accountBalance;
    }

    private void validateAmount(double amount) throws InvalidAmountException {
        if (amount<=0){
            throw new InvalidAmountException ("invalid amount");
        }
    }

    public double debit(double amount) throws InsufficientBalanceExceptoin, InvalidAmountException {
        validateAmount(amount);
        double finalBalance = (double) (accountBalance - amount);
        validateFinalBalance(finalBalance);
        this.transactions.debit ( amount , this.getHolderName () );
        accountBalance -= amount;
        return accountBalance;
    }

    private void validateFinalBalance(double finalBalance) throws InsufficientBalanceExceptoin {
        if (finalBalance < minBalance) {
            throw new InsufficientBalanceExceptoin ();
        }
    }

    public String getSummary() {
        Summary summary = new Summary ( holderName , accountNumber , accountBalance );
        return summary.toString ();
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions.getAllTransactions ();
    }

    public Transactions getCreditTransactions() {
        return this.transactions.filterCreditTransaction ();
    }

    public Transactions getDebitTransactions() {
        return this.transactions.filterDebitTransaction ();
    }
}
