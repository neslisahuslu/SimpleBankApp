package com.eteration.simplebanking.controller;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionStatus {


    public TransactionStatus(String status){

        setStatus(status);

    }
    String status;

    UUID approvalCode = UUID.randomUUID();
}
