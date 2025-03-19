package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.request.CreateAccountRequest;
import com.eteration.simplebanking.request.TransactionRequest;
import com.eteration.simplebanking.services.accountService.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/account/v1/")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;


    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {

        return ResponseEntity.ok(accountService.findAccount(accountNumber));

    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {

        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Account>> getAllAccounts() {

        List<Account> accounts = accountService.getAll();

        if (accounts.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        return ResponseEntity.ok(accounts); // 200 OK
    }

   @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber") String accountNumber,
                                                    @RequestBody TransactionRequest transactionRequest) {

        return ResponseEntity.ok(accountService.deposit(accountNumber,transactionRequest.getAmount()));
    }

    @PostMapping("/debit/{accountNumber}")

    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber") String accountNumber,
                                                   @RequestBody TransactionRequest transactionRequest) {

        return ResponseEntity.ok(accountService.withdraw(accountNumber, transactionRequest.getAmount()));
    }
}