package com.thoughtworks.account;

import org.junit.Before;
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
    Transactions transactions;
    @Before
    public void setUp() throws Exception {
        transactions = new Transactions ();
    }

    @Test
    public void shouldRecordDebitTransactions(){
        transactions.debit(1000,"manish",1000);
        transactions.credit ( 1000,"manu",1000 );
        assertThat(transactions.list,hasItem(new DebitTransaction (new Date (), "manish" , 1000 ,1000) ));
    }

    @Test
    public void shouldRecordCreditTransaction() {
        transactions.credit(1000,"manu",1000);
        assertThat(transactions.list,hasItem(new CreditTransaction (new Date (), "manu" , 1000 ,1000) ));
    }

    @Test
    public void shouldGiveEmptyListIfNoTxnDone() {
        ArrayList <Transaction> allTransactions = transactions.getAllTransactions ();
        assertTrue(allTransactions.isEmpty());
    }

    @Test
    public void shouldGiveTxnListIfTxnIsDone() {
        transactions.credit ( 1000,"mani" ,1000);
        ArrayList <Transaction> allTransactions = transactions.getAllTransactions ();
        ArrayList <Transaction> transactions1 = new ArrayList <> ();
        transactions1.add ( new CreditTransaction ( 1000,"mani",1000 ) );
        assertArrayEquals (transactions1.toArray (),allTransactions.toArray ());
        assertEquals( new HashSet <> ( transactions1 ), new HashSet <> ( allTransactions ));
    }

    @Test
    public void printTransactions() throws FileNotFoundException, UnsupportedEncodingException {
        CreditTransaction manish = new CreditTransaction(1000, "manish",1000);
        DebitTransaction manu = new DebitTransaction(1000, "manu",1000);
        ArrayList<String> result = new ArrayList<>();
        transactions.credit(1000,"manish",1000);
        transactions.debit(1000,"manu",1000);
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
    public void FilterTransactionByAmountGreaterThan() {
        transactions.credit ( 1000,"mani" ,1000);
        transactions.credit ( 1500,"manu" ,1000);
        Transactions filteredTransactions = transactions.filterByAmountGreaterThan ( 1000 );
        assertThat ( filteredTransactions.list, hasItems(new CreditTransaction ( 1000,"mani" ,1000),new CreditTransaction ( 1500,"manu" ,1000)) );
    }

    @Test
    public void FilterTransactionByAmountLessThan() {
        transactions.credit ( 1000,"mani",100 );
        transactions.credit ( 800,"manu",100 );
        transactions.debit ( 1500,"manu" ,100);
        Transactions filteredTransactions = transactions.filterByAmountLessThan ( 1000 );
        assertThat ( filteredTransactions.list, hasItems(new CreditTransaction ( 1000,"mani" ,100),new CreditTransaction ( 800,"manu" ,100)) );
    }

    @Test
    public void shouldFilterCreditTransactions() {
        transactions.credit ( 1000,"mani",10000 );
        transactions.credit ( 1500,"manu" ,10000);
        transactions.debit(1000,"mani",10000);
        Transactions creditTransactions = transactions.filterCreditTransaction ( );
        assertThat ( creditTransactions.list,hasItems ( new CreditTransaction ( 1500, "manu" ,10000),new CreditTransaction ( 1000,"mani" ,10000) ) );
    }
    @Test
    public void shouldFilterDebitTransactions() {
        transactions.debit ( 1000,"mani" ,1000);
        transactions.debit ( 1500,"manu",1000 );
        transactions.credit(1000,"mani",1000);
        Transactions debitTransactions = transactions.filterDebitTransaction ( );
        assertThat ( debitTransactions.list,hasItems ( new DebitTransaction ( 1000, "mani" ,1000),new DebitTransaction ( 1500,"manu",1000 ) ) );
    }

//    @Test
//    public void shouldFilterAllTransactionByDate() {
//
//    }
}
