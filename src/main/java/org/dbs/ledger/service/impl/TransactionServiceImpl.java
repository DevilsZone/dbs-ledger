package org.dbs.ledger.service.impl;

import org.dbs.ledger.dto.request.TransactionRequest;
import org.dbs.ledger.dto.response.TransactionResponse;
import org.dbs.ledger.dto.response.wrapper.ErrorResponse;
import org.dbs.ledger.enums.AccountBalanceOutputStatus;
import org.dbs.ledger.enums.AccountEntryOutputStatus;
import org.dbs.ledger.enums.ErrorCode;
import org.dbs.ledger.enums.TransactionStatus;
import org.dbs.ledger.exceptions.RestException;
import org.dbs.ledger.helper.AccountEntryHelper;
import org.dbs.ledger.helper.AccountHelper;
import org.dbs.ledger.model.output.AccountBalanceOutput;
import org.dbs.ledger.model.output.AccountEntryOutput;
import org.dbs.ledger.service.TransactionService;
import org.dbs.ledger.transformer.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.ACCOUNT_NOT_FOUND));
        }
        if (!toAccountBalance.accountBalanceOutputStatus().equals(AccountBalanceOutputStatus.SUCCESS)) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.ACCOUNT_NOT_FOUND));
        }
        if (fromAccountBalance.accountBalance().availableBalance() < transactionRequest.getAmount()) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.BALANCE_NOT_AVAILABLE));
        }

        AccountBalanceOutput accountBalanceOutput = accountHelper.updateAccountBalance(transactionTransformer.convertTransactionRequestToAccountInput(transactionRequest));
        if (!accountBalanceOutput.accountBalanceOutputStatus().equals(AccountBalanceOutputStatus.SUCCESS)) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR));
        }

        AccountEntryOutput accountEntry = accountEntryHelper.createAccountEntry(transactionTransformer.convertTransactionRequestToEntryInput(transactionRequest));
        if (!accountEntry.accountEntryOutputStatus().equals(AccountEntryOutputStatus.SUCCESS)) {
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR));
        }


        return transactionTransformer.convertAccountBalanceAndEntryToResponse(accountBalanceOutput, accountEntry, TransactionStatus.SUCCESS);
    }
}
