package com.thoughtworks.account;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DebitTransactionTest {
    @Test
    public void mustRecordTransactionDate() {
        Date date = new Date ();
        DebitTransaction debitTransaction = new DebitTransaction( date , "manish" , 1000 );
        assertThat(debitTransaction.getDate(),is(date));
    }
}
