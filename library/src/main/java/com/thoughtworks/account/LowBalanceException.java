package com.thoughtworks.account;

public class LowBalanceException extends Throwable {
    public LowBalanceException() {
        super ("initial balance can't be less than 1000");
    }
}
