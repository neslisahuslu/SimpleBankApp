package com.eteration.simplebanking.services.transactionService;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.stereotype.Service;


@Service
public interface ITransactionService {

    TransactionStatus save(Transaction transaction);
}
