package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping(value = "/account/v1/")
@RequiredArgsConstructor
@Transactional
public class AccountController {


    private final IAccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {

        return ResponseEntity.ok(accountService.findAccount(accountNumber));

    }


    @PostMapping("/credit/{accountNumber}") //para yatirma
    @Transactional
    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber") String accountNumber, Transaction transaction) {
        var account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account.deposit(transaction.getAmount()));
    }

    @PostMapping("/debit/{accountNumber}") //para cekme
    @Transactional
    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber") String accountNumber, Transaction transaction) {
        var account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account.withdraw(transaction.getAmount()));
	}
}