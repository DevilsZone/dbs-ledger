package org.dbs.ledger.service.impl;

import org.dbs.ledger.dto.request.TransactionRequest;
import org.dbs.ledger.dto.response.TransactionResponse;
import org.dbs.ledger.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    @Transactional
    public TransactionResponse transferFunds(TransactionRequest transactionRequest) {
        return null;
    }
}
