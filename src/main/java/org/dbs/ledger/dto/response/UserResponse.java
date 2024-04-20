package org.dbs.ledger.dto.response;

import lombok.*;
import org.dbs.ledger.dto.response.common.EmailResponse;
import org.dbs.ledger.dto.response.common.MobileResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserResponse {
    private String userId;

    private String name;

    private String profileUrl;

    private EmailResponse email;

    private MobileResponse mobile;
}