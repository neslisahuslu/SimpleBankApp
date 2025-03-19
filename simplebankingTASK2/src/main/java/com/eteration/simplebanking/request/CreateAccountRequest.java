package com.eteration.simplebanking.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

    private String owner;

    private String accountNumber;
}
