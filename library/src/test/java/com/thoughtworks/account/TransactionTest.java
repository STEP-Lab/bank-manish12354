package com.thoughtworks.account;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {
    @Test
    public void mustRecordTransactionDate() {
        Date date = new Date ();
        Transaction debitTransaction = new DebitTransaction( date , "manish",1000 , 1000 );
        assertThat(debitTransaction.getDate(),is(date));
    }
    @Test
    public void shouldGiveAvailableBalance() {
        Transaction debitTransaction = new DebitTransaction ( 1000 , "manish" , 2000 );
        assertThat ( debitTransaction.getAvailableBalance(),is ((double) 2000 ) );
    }
}
