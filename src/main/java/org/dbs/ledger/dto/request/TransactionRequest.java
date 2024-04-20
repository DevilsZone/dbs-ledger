package org.dbs.ledger.dto.request;

import lombok.*;
import org.dbs.ledger.enums.CurrencyName;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TransactionRequest {
    private String fromAccountId;

    private String toAccountId;

    private Integer amount;

    private CurrencyName currencyName;
}
