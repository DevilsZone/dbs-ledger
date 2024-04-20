package org.dbs.ledger.dto.request.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public sealed abstract class SignInRequest permits EmailSignInRequest, MobileSignInRequest {
    private String password;
}
