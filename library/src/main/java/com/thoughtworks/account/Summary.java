package com.thoughtworks.account;

class Summary{
    private final String accountNumber;
    private final double accountBalance;
    private final String holderName;

    Summary(String holderName, String accountNumber, double accountBalance){
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
