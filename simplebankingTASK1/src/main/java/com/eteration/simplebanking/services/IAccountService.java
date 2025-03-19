package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public interface IAccountService {

 Account findAccount(String accountNumber);


}

