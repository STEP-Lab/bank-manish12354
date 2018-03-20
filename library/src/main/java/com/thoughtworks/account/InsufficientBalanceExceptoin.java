package com.thoughtworks.account;

public class InsufficientBalanceExceptoin extends Throwable {
    public InsufficientBalanceExceptoin() {
        super ("insufficient fund!" );
    }
}
