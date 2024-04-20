package org.dbs.ledger.transformer;

import org.dbs.ledger.annotation.Transformer;
import org.dbs.ledger.dto.request.TransactionRequest;
import org.dbs.ledger.dto.response.TransactionResponse;
import org.dbs.ledger.enums.TransactionStatus;
import org.dbs.ledger.model.input.AccountBalanceUpdateInput;
import org.dbs.ledger.model.input.AccountEntryInput;
import org.dbs.ledger.model.output.AccountBalanceOutput;
import org.dbs.ledger.model.output.AccountEntryOutput;

@Transformer
public class TransactionTransformer {
    public AccountBalanceUpdateInput convertTransactionRequestToAccountInput(TransactionRequest transactionRequest) {
        return new AccountBalanceUpdateInput(
                transactionRequest.getFromAccountId(),
                transactionRequest.getToAccountId(),
                transactionRequest.getTransactionType(),
                transactionRequest.getAmount()
        );
    }

    public AccountEntryInput convertTransactionRequestToEntryInput(TransactionRequest transactionRequest) {
        return new AccountEntryInput(
                transactionRequest.getFromAccountId(),
                transactionRequest.getToAccountId(),
                transactionRequest.getTransactionType(),
                transactionRequest.getAmount()
        );
    }

    public TransactionResponse convertAccountBalanceAndEntryToResponse(AccountBalanceOutput accountBalanceOutput, AccountEntryOutput accountEntry, TransactionStatus transactionStatus) {
        return TransactionResponse
                .builder()
                .accountId(accountBalanceOutput.accountBalance().accountId())
                .transactionId(accountEntry.accountEntryId())
                .transactionStatus(transactionStatus)
                .availableBalance(accountBalanceOutput.accountBalance().availableBalance())
                .build();
    }
}
