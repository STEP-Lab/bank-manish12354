package com.thoughtworks.account;


import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumException.class)
    public void invalidAccNumTestForAlphabates() throws InvalidAccountNumException, LowBalanceException {
        new Account ("manish", AccountNumber.create ( "1we3-33e2" ) ,812375);
    }

    @Test(expected = InvalidAccountNumException.class)
    public void invalidAccNumTest() throws InvalidAccountNumException, LowBalanceException {
        new Account("manish", AccountNumber.create ( "1823-332" ) ,812375);
    }
}
