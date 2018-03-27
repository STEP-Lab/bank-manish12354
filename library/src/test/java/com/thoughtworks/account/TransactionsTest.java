package com.thoughtworks.account;

import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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

    @Test
    public void shouldGiveEmptyListIfNoTxnDone() {
        Transactions transactions = new Transactions ();
        ArrayList <Transaction> allTransactions = transactions.getAllTransactions ();
        assertTrue(allTransactions.isEmpty());
    }

    @Test
    public void shouldGiveTxnListIfTxnIsDone() {
        Transactions transactions = new Transactions ();
        transactions.credit ( 1000,"mani" );
        ArrayList <Transaction> allTransactions = transactions.getAllTransactions ();
        ArrayList <Transaction> transactions1 = new ArrayList <> ();
        transactions1.add ( new CreditTransaction ( 1000,"mani" ) );
        assertArrayEquals (transactions1.toArray (),allTransactions.toArray ());
        assertEquals( new HashSet <> ( transactions1 ), new HashSet <> ( allTransactions ));
    }

    @Test
    public void printTransactions() throws FileNotFoundException, UnsupportedEncodingException {
        Transactions transactions = new Transactions ();
        CreditTransaction manish = new CreditTransaction(1000, "manish");
        DebitTransaction manu = new DebitTransaction(1000, "manu");
        ArrayList<String> result = new ArrayList<>();
        transactions.credit(1000,"manish");
        transactions.debit(1000,"manu");
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8"){
            @Override
            public void println(String x) {
                result.add(x);
            }
        };
        transactions.print(writer);
        writer.close();
        assertThat(result,hasItems(manish.toString(),manu.toString()));
    }


    @Test
    public void FilterTransactionByAmount() {
        Transactions transactions = new Transactions ();
        transactions.credit ( 1000,"mani" );
        transactions.credit ( 1500,"manu" );
        Transactions filteredTransactions = transactions.filterByAmountGreaterThan ( 1000 );
        assertThat ( filteredTransactions.list, hasItems(new CreditTransaction ( 1000,"mani" ),new CreditTransaction ( 1500,"manu" )) );
    }

    @Test
    public void shouldFilterCreditTransactions() {
        Transactions transactions = new Transactions ();
        transactions.credit ( 1000,"mani" );
        transactions.credit ( 1500,"manu" );
        transactions.debit(1000,"mani");
        Transactions creditTransactions = transactions.filterCreditTransaction ( );
        assertThat ( creditTransactions.list,hasItems ( new CreditTransaction ( 1500, "manu" ),new CreditTransaction ( 1000,"mani" ) ) );
    }
    @Test
    public void shouldFilterDebitTransactions() {
        Transactions transactions = new Transactions ();
        transactions.debit ( 1000,"mani" );
        transactions.debit ( 1500,"manu" );
        transactions.credit(1000,"mani");
        Transactions debitTransactions = transactions.filterDebitTransaction ( );
        assertThat ( debitTransactions.list,hasItems ( new DebitTransaction ( 1000, "mani" ),new DebitTransaction ( 1500,"manu" ) ) );
    }
}
