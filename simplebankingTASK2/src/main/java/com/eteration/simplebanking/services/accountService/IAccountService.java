package com.eteration.simplebanking.services.accountService;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.request.CreateAccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IAccountService {

 Account findAccount(String accountNumber);

 Account save (Account account);

 boolean existsByAccountNumber(String accountNumber);

 Account createAccount(CreateAccountRequest createAccountRequest);

 List<Account> getAll();

 TransactionStatus withdraw(String accountNumber,double amount);

 TransactionStatus deposit(String accountNumber,double amount);



}

