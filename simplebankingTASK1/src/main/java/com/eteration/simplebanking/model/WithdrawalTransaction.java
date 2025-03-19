package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction{

    public WithdrawalTransaction(){
        setType("WithdrawalTransaction");
    }

    public WithdrawalTransaction(double amount){
         super(amount);
        setType("WithdrawalTransaction");
    }


    @Override
    public void process(Account account) {
        if (account.getBalance() < this.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        account.setBalance(account.getBalance()-this.getAmount());
    }
}


