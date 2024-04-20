package org.dbs.ledger.transformer;

import org.dbs.ledger.annotation.Transformer;
import org.dbs.ledger.enums.AccountBalanceOutputStatus;
import org.dbs.ledger.model.Account;
import org.dbs.ledger.model.output.AccountBalance;
import org.dbs.ledger.model.output.AccountBalanceOutput;

@Transformer
public class AccountTransformer {

    public AccountBalanceOutput convertAccountToOutput(Account account) {
        return new AccountBalanceOutput(AccountBalanceOutputStatus.FOUND, convertAccountToAccountBalance(account));
    }

    public AccountBalance convertAccountToAccountBalance(Account account) {
        return new AccountBalance(account.getId(), account.getAccountBalance());
    }

    public void updateAccountBalance(Account account, Integer balanceToAdd) {
        account.setAccountBalance(account.getAccountBalance() + balanceToAdd);
    }
}
