package org.dbs.ledger.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.dbs.ledger.enums.CurrencyName;
import org.dbs.ledger.enums.TransactionType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TransactionRequest {
    @NotBlank
    private String fromAccountId;

    @NotBlank
    private String toAccountId;

    @Min(1)
    private Integer amount;

    @NotNull
    private CurrencyName currencyName;

    @NotNull
    private TransactionType transactionType;
}
