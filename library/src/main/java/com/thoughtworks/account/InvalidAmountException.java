package com.thoughtworks.account;

public class InvalidAmountException extends Throwable {
    public InvalidAmountException(String message) {
        super ( message );
    }
}
