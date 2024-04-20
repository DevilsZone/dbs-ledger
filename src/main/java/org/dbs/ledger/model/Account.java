package org.dbs.ledger.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dbs.ledger.enums.AccountType;
import org.dbs.ledger.enums.CurrencyName;
import org.dbs.ledger.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.ACCOUNT_TABLE_NAME)
public class Account extends BaseEntity {
    private AccountType accountType;

    private String userId;

    private CurrencyName currencyName;

    private Integer accountBalance;
}
