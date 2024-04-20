package org.dbs.ledger.dto.request.user;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.dbs.ledger.dto.request.common.EmailRequest;

@Getter
@Setter
@SuperBuilder
public final class EmailSignInRequest extends SignInRequest{
    private EmailRequest emailRequest;
}
