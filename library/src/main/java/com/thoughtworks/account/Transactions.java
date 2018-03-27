package com.thoughtworks.account;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {
    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(double amount, String name) {
        this.list.add(new DebitTransaction(amount,name));
    }

    public void credit(double amount , String name) {
        this.list.add(new CreditTransaction(amount,name));
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
            if (transaction.isDebitTransaction()){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

}
