package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionStatus {

    public TransactionStatus(String status, UUID approvalCode){
        setStatus(status);
        setApprovalCode(approvalCode);
    }


    String status;

    UUID approvalCode;

    public String getStatus() {
        return status;
    }
}
