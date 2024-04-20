package org.dbs.ledger.service.impl;

import org.dbs.ledger.dto.request.TransactionRequest;
import org.dbs.ledger.dto.response.TransactionResponse;
import org.dbs.ledger.enums.AccountBalanceOutputStatus;
import org.dbs.ledger.enums.AccountEntryOutputStatus;
import org.dbs.ledger.enums.TransactionStatus;
import org.dbs.ledger.helper.AccountEntryHelper;
import org.dbs.ledger.helper.AccountHelper;
import org.dbs.ledger.model.output.AccountBalanceOutput;
import org.dbs.ledger.model.output.AccountEntryOutput;
import org.dbs.ledger.service.TransactionService;
import org.dbs.ledger.transformer.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionTransformer transactionTransformer;

    private final AccountHelper accountHelper;

    private final AccountEntryHelper accountEntryHelper;

    @Autowired
    public TransactionServiceImpl(TransactionTransformer transactionTransformer, AccountHelper accountHelper, AccountEntryHelper accountEntryHelper) {
        this.transactionTransformer = transactionTransformer;
        this.accountHelper = accountHelper;
        this.accountEntryHelper = accountEntryHelper;
    }

    @Override
    @Transactional
    public TransactionResponse transferFunds(TransactionRequest transactionRequest) {
        AccountBalanceOutput fromAccountBalance = accountHelper.getAccountBalance(transactionRequest.getFromAccountId());
        AccountBalanceOutput toAccountBalance = accountHelper.getAccountBalance(transactionRequest.getToAccountId());
        if (!fromAccountBalance.accountBalanceOutputStatus().equals(AccountBalanceOutputStatus.SUCCESS)) {
            throw new RuntimeException();
        }
        if (!toAccountBalance.accountBalanceOutputStatus().equals(AccountBalanceOutputStatus.SUCCESS)) {
            throw new RuntimeException();
        }
        if (fromAccountBalance.accountBalance().availableBalance() < transactionRequest.getAmount()) {
            throw new RuntimeException();
        }

        AccountBalanceOutput accountBalanceOutput = accountHelper.updateAccountBalance(transactionTransformer.convertTransactionRequestToAccountInput(transactionRequest));
        if (!accountBalanceOutput.accountBalanceOutputStatus().equals(AccountBalanceOutputStatus.SUCCESS)) {
            throw new RuntimeException();
        }

        AccountEntryOutput accountEntry = accountEntryHelper.createAccountEntry(transactionTransformer.convertTransactionRequestToEntryInput(transactionRequest));
        if (!accountEntry.accountEntryOutputStatus().equals(AccountEntryOutputStatus.SUCCESS)) {
            throw new RuntimeException();
        }


        return transactionTransformer.convertAccountBalanceAndEntryToResponse(accountBalanceOutput, accountEntry, TransactionStatus.SUCCESS);
    }
}
