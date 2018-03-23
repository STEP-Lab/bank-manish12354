package com.thoughtworks.account;

public class LowBalanceException extends Throwable {
    public LowBalanceException(String message) {
        super (message);
    }
}
