package org.dbs.ledger.helper;

import org.dbs.ledger.model.input.AccountEntryInput;
import org.dbs.ledger.model.output.AccountEntryOutput;

public interface AccountEntryHelper {
    AccountEntryOutput createAccountEntry(AccountEntryInput accountEntryInput);
}
