package com.thoughtworks.account;

public class AccountNumber {
    final String accountNumber;

    private AccountNumber(String accountNumber) throws InvalidAccountNumException {
        this.accountNumber = accountNumber;
    }

    public static AccountNumber create(String accountNumber) throws InvalidAccountNumException {
        validateAccountNumber(accountNumber,"invalid account number pattern");
        return new AccountNumber ( accountNumber );
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    private static void validateAccountNumber(String accountNumber, String message) throws InvalidAccountNumException {
        boolean match = accountNumber.matches("^\\d{4}\\-\\d{4}$");
        if(!match){
            throw new InvalidAccountNumException ();
        }
    }
}
