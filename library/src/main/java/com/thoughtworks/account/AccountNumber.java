package com.thoughtworks.account;

public class AccountNumber {
    public AccountNumber(String accountNumber) throws InvalidAccountNumException {
        validateAccountNumber(accountNumber,"invalid account number pattern");
    }

    private void validateAccountNumber(String accountNumber,String message) throws InvalidAccountNumException {
        boolean match = accountNumber.matches("^\\d{4}\\-\\d{4}$");
        if(!match){
            throw new InvalidAccountNumException ();
        }
    }
}
