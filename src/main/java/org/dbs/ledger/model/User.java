package org.dbs.ledger.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dbs.ledger.model.common.Email;
import org.dbs.ledger.model.common.Mobile;
import org.dbs.ledger.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder
@Document(MongoConstants.USER_TABLE_NAME)
public class User {
    private String name;

    private String profileUrl;

    private Email email;

    private Mobile mobile;
}
