package org.dbs.ledger.model.input;

public record AccountEntryInput(
        String fromAccountId,

        String toAccountId,

        Integer transferredAmount
) {
}
