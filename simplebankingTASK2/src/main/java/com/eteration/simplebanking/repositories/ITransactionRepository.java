package com.eteration.simplebanking.repositories;

import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITransactionRepository  extends JpaRepository<Transaction, UUID> {


}
