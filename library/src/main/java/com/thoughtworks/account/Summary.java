package com.thoughtworks.account;

class Summary{
    private final AccountNumber accountNumber;
    private final double accountBalance;
    private final String holderName;

    Summary(String holderName, AccountNumber accountNumber, double accountBalance){
        this.holderName = holderName;
        this.accountNumber= accountNumber;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", holderName=" + holderName;
    }
}
