package com.thoughtworks.account;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    private Account account;
    @Before
    public void setUp() throws LowBalanceException, InvalidAccountNumException {
       account = Account.CreateAcc ( "Manish" , AccountNumber.create ( "1234-5636" ) , 1000 );
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
    public void creditTest() throws LowBalanceException, InvalidAccountNumException, InvalidAmountException {
        Account account = Account.CreateAcc ( "manish" , AccountNumber.create ( "1234-4567" ) , 2000 );
        assertThat ( account.credit(1000), is ( 3000d));
        assertThat ( account.credit ( 100.5 ),is ( 3100.5 ) );
    }

    @Test
    public void debitTest() throws LowBalanceException, InsufficientBalanceExceptoin, InvalidAccountNumException, InvalidAmountException {
        Account account = Account.CreateAcc ( "manu" , AccountNumber.create ( "1234-5636" ) , 2000 );
        assertThat ( account.debit(200), is ((double) 1800 ) );
    }

    @Test(expected = LowBalanceException.class)
    public void lowBalanceTest() throws LowBalanceException, InvalidAccountNumException {
        Account.CreateAcc ( "manu", AccountNumber.create ( "1234-5636" ) , 200 );
    }

    @Test(expected = InsufficientBalanceExceptoin.class)
    public void insufficientBalTest() throws LowBalanceException, InsufficientBalanceExceptoin, InvalidAccountNumException, InvalidAmountException {
        Account account = Account.CreateAcc ( "manu" , AccountNumber.create ( "1234-5636" ) , 2000 );
        account.debit(2000);
    }

    @Test
    public void getSummaryTest () throws InvalidAccountNumException, LowBalanceException {
        Account account = Account.CreateAcc ( "manish Yadav" , AccountNumber.create ( "1234-5636" ) , 3000 );
        assertThat ( account.getSummary (),is ( "accountNumber=1234-5636, accountBalance=3000.0, holderName=manish Yadav" ) );
    }

    @Test()
    public void checkIfWithdrawIsAllowed() throws InvalidAccountNumException, LowBalanceException, InsufficientBalanceExceptoin, InvalidAmountException {
        final Account account = Account.CreateAcc ( "manish" , AccountNumber.create ( "1234-5636" ) , 4000 );
        account.debit(1000);
        assertThat ( account.getBalance (),is ( 3000.0d ) );
    }

    @Test
    public void shouldRecordCreditTransaction() throws InvalidAccountNumException, LowBalanceException, InvalidAmountException {
        Account account = Account.CreateAcc ( "manish" , AccountNumber.create ( "1234-5636" ) , 2000 );
        account.credit ( 1000 );
        assertThat(account.getTransactions(),hasItem(new CreditTransaction (new Date (), "manish" , 1000 ) ));
    }

    @Test
    public void shouldRecordDebitTransaction() throws InvalidAccountNumException, LowBalanceException, InsufficientBalanceExceptoin, InvalidAmountException {
        Account account = Account.CreateAcc ( "manish" , AccountNumber.create ( "1234-5636" ) , 2000 );
        account.debit (1000 );
        assertThat(account.getTransactions(),hasItem(new DebitTransaction (new Date (), "manish" , 1000 )));
    }

    @Test(expected = InvalidAmountException.class)
    public void invalidAmountTestForZeroAmount() throws InvalidAccountNumException, LowBalanceException, InvalidAmountException {
        Account account = Account.CreateAcc ( "manoj" , AccountNumber.create ( "1234-8765" ) , 2000 );
        account.credit ( 0 );
    }

    @Test(expected = InvalidAmountException.class)
    public void invalidAmountTestForNegativeAmount() throws InvalidAmountException, InvalidAccountNumException, LowBalanceException {
        Account account = Account.CreateAcc ( "manoj" , AccountNumber.create ( "1234-8765" ) , 2000 );
        account.credit ( -100 );
    }
}
