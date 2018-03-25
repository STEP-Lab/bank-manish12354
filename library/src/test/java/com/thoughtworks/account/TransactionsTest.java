package com.thoughtworks.account;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
    @Test
    public void shouldRecordDebitTransactions(){
        Transactions transactions = new Transactions();
        transactions.debit(1000,"manish");
        transactions.credit ( 1000,"manu" );
//        System.out.println (transactions.list);
        assertThat(transactions.list,hasItem(new DebitTransaction (new Date (), "manish" , 1000 ) ));
    }

    @Test
    public void shouldRecordCreditTransaction() {
        Transactions transactions = new Transactions ();
        transactions.credit(1000,"manu");
        assertThat(transactions.list,hasItem(new CreditTransaction (new Date (), "manu" , 1000 ) ));
    }
}
