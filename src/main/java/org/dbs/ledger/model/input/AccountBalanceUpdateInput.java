package org.dbs.ledger.model.input;

public record AccountBalanceUpdateInput(
        String fromAccountId,

        String toAccountId,

        Integer transferredAmount
) {
}
