package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class PhoneBillPaymentTransaction extends BillPaymentTransaction {
    private String payee;
    private String phoneNumber;

    public PhoneBillPaymentTransaction() {
        super();
    }


    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPayee() {
        return payee;
    }

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount ){
        super();
        this.setAmount(amount);
        this.setPayee(payee);
        this.setType("PhoneBillPaymentTransaction");
        this.setPhoneNumber(phoneNumber);


    }
}
