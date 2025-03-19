package com.eteration.simplebanking.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

@AllArgsConstructor
@Entity
public class DepositTransaction extends Transaction {


    public DepositTransaction(double amount, String accountNumber){
        setAmount(amount);
        setAccountNumber(accountNumber);
        setApprovalCode(UUID.randomUUID());
        setType("DepositTransaction");
    }

}
