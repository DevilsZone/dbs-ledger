package org.dbs.ledger.helper;

import org.dbs.ledger.model.input.AccountBalanceUpdateInput;
import org.dbs.ledger.model.output.AccountBalanceOutput;

public interface AccountHelper {
    AccountBalanceOutput updateAccountBalance(AccountBalanceUpdateInput accountBalanceUpdateInput);

}
