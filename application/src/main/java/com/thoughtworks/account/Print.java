package com.thoughtworks.account;

public class Print {
    public static void main(String[] args) throws InvalidAccountNumException, LowBalanceException {
        Account account = new Account ( "manish" , AccountNumber.create ( "3465-3266" ) , 8136 );
        System.out.println (account.getBalance ());
    }
}

