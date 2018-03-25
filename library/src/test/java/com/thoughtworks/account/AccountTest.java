package com.thoughtworks.account;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    Account account;
    @Before
    public void setUp() throws Exception, LowBalanceException, InvalidAccountNumException {
        account = new Account ( "Manish" , new AccountNumber ( "1234-5636" ) , 1000 );
    }
    @Test
    public void getBalanceTest(){
        assertThat(account.getBalance () , is((double) 1000));
    }

    @Test
    public void getHolderName() {
        assertThat ( account.getHolderName(), is ( "Manish" ));
    }

    @Test
    public void creditTest() throws LowBalanceException, InvalidAccountNumException {
        Account account = new Account ( "manish" , new AccountNumber ( "1234-5636" ) , 5000 );
        assertThat ( account.credit(1000), is ( (double) 6000 ) );
        assertThat ( account.credit ( 100.5 ),is ( (double) 6100.5 ) );
    }

    @Test
    public void debitTest() throws LowBalanceException, InsufficientBalanceExceptoin, InvalidAccountNumException {
        Account account = new Account ( "manu" , new AccountNumber ( "1234-5636" ) , 2000 );
        assertThat ( account.debit(200), is ((double) 1800 ) );
    }

    @Test(expected = LowBalanceException.class)
    public void lowBalanceTest() throws LowBalanceException, InvalidAccountNumException {
        new Account ( "manu",new AccountNumber ( "1234-5636" ), 200 );
    }

    @Test(expected = InsufficientBalanceExceptoin.class)
    public void insufficientBalTest() throws LowBalanceException, InsufficientBalanceExceptoin, InvalidAccountNumException {
        Account account = new Account ( "manu" , new AccountNumber ( "1234-5636" ) , 2000 );
        account.debit(2000);
    }

//    @Test
//    public void getSummaryTest () throws InvalidAccountNumException, LowBalanceException {
//        Account account = new Account ( "manish Yadav" , new AccountNumber ( "1234-5636" ) , 3000 );
//        assertThat ( account.getSummary (),is ( "accountNumber=1234-5678, accountBalance=3000.0, holderName=manish Yadav" ) );
//    }

    @Test()
    public void checkIfWithdrawIsAllowed() throws InvalidAccountNumException, LowBalanceException, InsufficientBalanceExceptoin {
        final Account account1 = new Account ( "manish" , new AccountNumber ( "1234-5636" ) , 4000 );
        account1.debit(1000);
        assertThat ( account1.getBalance (),is ( 3000.0d ) );
    }

    @Test
    public void shouldRecordCreditTransaction() throws InvalidAccountNumException, LowBalanceException {
        Account account = new Account ( "manish" , new AccountNumber ( "1234-5636" ) , 2000 );
        account.credit ( 1000 );
        assertThat(account.transactions.list,hasItem(new CreditTransaction (new Date (), "manish" , 1000 ) ));
    }
}
