package org.dbs.ledger.model.input;

public record AccountBalanceUpdateInput(
        String accountId,
        Integer transferredAmount
) {
}
