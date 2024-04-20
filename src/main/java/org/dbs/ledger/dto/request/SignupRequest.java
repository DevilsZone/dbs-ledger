package org.dbs.ledger.dto.request;

import lombok.*;
import org.dbs.ledger.dto.request.common.EmailRequest;
import org.dbs.ledger.dto.request.common.MobileRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SignupRequest {
    private String name;

    private String profileUrl;

    private EmailRequest email;

    private MobileRequest mobile;

    private String password;
}
