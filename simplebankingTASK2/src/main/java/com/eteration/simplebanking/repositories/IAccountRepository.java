package com.eteration.simplebanking.repositories;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAccountRepository  extends JpaRepository<Account, String>  {

    boolean existsByAccountNumber(String accountNumber);


}
