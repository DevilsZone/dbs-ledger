package org.dbs.ledger.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dbs.ledger.enums.CurrencyCode;
import org.dbs.ledger.enums.CurrencyName;
import org.dbs.ledger.util.MongoConstants;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.CURRENCY_TABLE_NAME)
public class Currency extends BaseEntity {
    @Indexed(unique = true)
    private CurrencyName currencyName;

    private CurrencyCode currencyCode;

    private Integer decimalPlaces;
}
