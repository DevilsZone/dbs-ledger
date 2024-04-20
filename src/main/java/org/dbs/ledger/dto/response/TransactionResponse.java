package org.dbs.ledger.dto.response;

import lombok.*;
import org.dbs.ledger.enums.TransactionStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TransactionResponse {
    private String transactionId;

    private TransactionStatus transactionStatus;

    private String accountId;

    private Integer availableBalance;
}
