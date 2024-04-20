package org.dbs.ledger.dto.request.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dbs.ledger.dto.request.common.MobileRequest;

@Getter
@Setter
@SuperBuilder
public final class MobileSignInRequest extends SignInRequest {
    private MobileRequest mobileRequest;
}
