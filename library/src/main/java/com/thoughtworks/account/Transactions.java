package com.thoughtworks.account;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

public class Transactions {
    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(double amount, String name,double availableBalance) {
        this.list.add(new DebitTransaction(amount,name,availableBalance));
    }

    public void credit(double amount , String name,double availableBalance) {
        this.list.add(new CreditTransaction(amount,name,availableBalance));
    }

    public ArrayList <Transaction> getAllTransactions() {
        return list;
    }

    public Transactions filterByAmountGreaterThan(double amount) {
        Transactions transactions = new Transactions ();
        for (Transaction transaction: list) {
            if (transaction.getAmount ()>=amount){
            transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions filterByAmountLessThan(double amount) {
        Transactions transactions = new Transactions ();
        for (Transaction transaction: list) {
            if (transaction.getAmount ()<=amount){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void print(PrintWriter writer) {
        for (Transaction transaction: list) {
            writer.println(transaction.toString());
        }
    }

    public Transactions filterCreditTransaction() {
        Transactions transactions = new Transactions ();
        for (Transaction transaction: list) {
            if (transaction.isCreditTransaction()){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions filterDebitTransaction() {
        Transactions transactions = new Transactions ();
        for (Transaction transaction: list) {
            /// move logic to transaction
            if (transaction.isDebitTransaction()){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transactions)) return false;
        Transactions that = (Transactions) o;
        return Objects.equals ( list , that.list );
    }

    @Override
    public int hashCode() {

        return Objects.hash ( list );
    }
}
