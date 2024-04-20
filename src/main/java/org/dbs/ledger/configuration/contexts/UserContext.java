package org.dbs.ledger.configuration.contexts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Getter
@Setter
public class UserContext {
    private String token;

    private String userId;
}
