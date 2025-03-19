package com.eteration.simplebanking.services.transactionService;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repositories.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService implements ITransactionService{


    private final ITransactionRepository transactionRepository;

    @Override
    public TransactionStatus save(Transaction transaction) {
        transactionRepository.save(transaction);
        return new TransactionStatus("OK",transaction.getApprovalCode());
    }
}
