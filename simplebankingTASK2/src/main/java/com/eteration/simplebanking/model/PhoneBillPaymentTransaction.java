package com.eteration.simplebanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PhoneBillPaymentTransaction")
@Entity
public class PhoneBillPaymentTransaction extends PaymentTransaction {

    private String payee;

    @Transient
    private String phoneNumber;


    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount){

        setPayee(payee);
        setPhoneNumber(phoneNumber);
        setAmount(amount);

    }
}
