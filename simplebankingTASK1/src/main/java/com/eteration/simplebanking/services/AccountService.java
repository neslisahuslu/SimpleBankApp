package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repositories.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

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
}
