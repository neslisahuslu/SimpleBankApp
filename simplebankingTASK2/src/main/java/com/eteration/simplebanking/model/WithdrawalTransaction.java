package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@Entity
public class WithdrawalTransaction extends Transaction{

    public WithdrawalTransaction(double amount,String accountNumber){
        setAmount(amount);
        setAccountNumber(accountNumber);
        setType("WithdrawalTransaction");
    }


}


