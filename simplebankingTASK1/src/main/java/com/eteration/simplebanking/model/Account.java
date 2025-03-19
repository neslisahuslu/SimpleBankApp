package com.eteration.simplebanking.model;

import com.eteration.simplebanking.controller.TransactionStatus;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "account")
public class Account implements Serializable {



    @Column(name="owner")
    private String owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_number")
    private String accountNumber;

    @Column(name="balance")
    private double balance = 0.0;

    @Transient
    @OneToMany(targetEntity = Transaction.class)
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {

    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Account(String owner, String accountNumber){
        setOwner(owner);
        setAccountNumber(accountNumber);

    }

    public TransactionStatus deposit (double amount){
        setBalance(getBalance()+amount);
        return new TransactionStatus("OK");
    }

    public TransactionStatus withdraw(double amount){
        if (this.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        setBalance(getBalance()-amount);
        return new TransactionStatus("OK");

    }

    public void post(Transaction transaction) {
        try {
            transaction.process(this);
            transactions.add(transaction);
        } catch (InsufficientBalanceException e) {
            // Handle the exception (e.g., log it)
            System.err.println("Insufficient balance: " + e.getMessage());
        }

    }
}
