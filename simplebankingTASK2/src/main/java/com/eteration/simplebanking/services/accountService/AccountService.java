package com.eteration.simplebanking.services.accountService;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repositories.IAccountRepository;
import com.eteration.simplebanking.request.CreateAccountRequest;
import com.eteration.simplebanking.services.transactionService.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements IAccountService {


    private final IAccountRepository accountRepository;
    private final ITransactionService transactionService;

    @Override
    public Account findAccount(String accountNumber) {

        try {

            Account account = accountRepository.findById(accountNumber)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account not found with accountNumber: " + accountNumber));
            return account;
        } catch (HttpClientErrorException exception) {
            return null;
        } catch (Exception exception) {

            throw new RuntimeException("Error finding account by accountNumber: " + exception.getMessage());
        }
    }

    @Override
    public Account save(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        try {
            String accountNumber = createAccountRequest.getAccountNumber();

            if (existsByAccountNumber(accountNumber)) {
                throw new RuntimeException("Error: Account Number is already in use!");
            }

            Account account = new Account(
                    createAccountRequest.getOwner(),
                    createAccountRequest.getAccountNumber());

            return save(account);
        } catch (Exception exception) {
            throw new RuntimeException("Error happened while creating the account: " + exception.getMessage());
        }
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }


    @Override
    public TransactionStatus withdraw(String accountNumber,double amount) {
        try {
            var account = this.findAccount(accountNumber);
            if (account.getBalance()<amount)
                throw new InsufficientBalanceException("Insufficient balance for withdraw");
            else {
                account.setBalance(account.getBalance()-amount);
                Transaction transaction = new WithdrawalTransaction(amount,accountNumber);
                accountRepository.save(account);
                return transactionService.save(transaction);
            }

        }catch (Exception exception){
            throw new RuntimeException("Error happened while withdraw process: " + exception.getMessage());

        }
    }

    @Override
    public TransactionStatus deposit(String accountNumber,double amount) {
        try {
            var account = this.findAccount(accountNumber);
            account.setBalance(account.getBalance()+amount);
            Transaction transaction = new DepositTransaction(amount,accountNumber);
            accountRepository.save(account);
            return transactionService.save(transaction);

        }catch (Exception exception){
            throw new RuntimeException("Error happened while deposit process: " + exception.getMessage());

        }
    }

}
