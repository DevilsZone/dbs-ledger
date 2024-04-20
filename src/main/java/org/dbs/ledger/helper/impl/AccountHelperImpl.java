package org.dbs.ledger.helper.impl;

import org.dbs.ledger.annotation.Helper;
import org.dbs.ledger.enums.AccountBalanceOutputStatus;
import org.dbs.ledger.enums.Status;
import org.dbs.ledger.helper.AccountHelper;
import org.dbs.ledger.model.Account;
import org.dbs.ledger.model.input.AccountBalanceUpdateInput;
import org.dbs.ledger.model.output.AccountBalanceOutput;
import org.dbs.ledger.repository.AccountRepository;
import org.dbs.ledger.transformer.AccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Helper
public class AccountHelperImpl implements AccountHelper {
    private final AccountRepository accountRepository;

    private final AccountTransformer accountTransformer;

    @Autowired
    public AccountHelperImpl(AccountRepository accountRepository, AccountTransformer accountTransformer) {
        this.accountRepository = accountRepository;
        this.accountTransformer = accountTransformer;
    }

    @Override
    public AccountBalanceOutput updateAccountBalance(AccountBalanceUpdateInput accountBalanceUpdateInput) {
        Optional<Account> optionalFromAccount = accountRepository.findAccountByIdAndStatus(accountBalanceUpdateInput.fromAccountId(), Status.ACTIVE);
        if (optionalFromAccount.isEmpty()) {
            return AccountBalanceOutput.createFailedAccount(AccountBalanceOutputStatus.NOT_FOUND);
        }
        Optional<Account> optionalToAccount = accountRepository.findAccountByIdAndStatus(accountBalanceUpdateInput.toAccountId(), Status.ACTIVE);
        if (optionalToAccount.isEmpty()) {
            return AccountBalanceOutput.createFailedAccount(AccountBalanceOutputStatus.FAILED);
        }
        Account toAccount = optionalToAccount.get();
        Account fromAccount = optionalFromAccount.get();
        accountTransformer.updateAccountBalance(toAccount, accountBalanceUpdateInput.transferredAmount());
        accountTransformer.updateAccountBalance(fromAccount, accountBalanceUpdateInput.transferredAmount()*-1);

        accountRepository.save(toAccount);
        Account updatedAccount = accountRepository.save(fromAccount);
        return accountTransformer.convertAccountToOutput(updatedAccount);
    }

    @Override
    public AccountBalanceOutput getAccountBalance(String accountId) {
        Optional<Account> optionalAccount = accountRepository.findAccountByIdAndStatus(accountId, Status.ACTIVE);
        if (optionalAccount.isEmpty()) {
            return AccountBalanceOutput.createFailedAccount(AccountBalanceOutputStatus.NOT_FOUND);
        }
        Account account = optionalAccount.get();
        return accountTransformer.convertAccountToOutput(account);
    }
}
