package com.thoughtworks.account;

public class InvalidAccountNumException extends Throwable {
    public InvalidAccountNumException() {
        super ( "Invalid account number !" );
    }
}
