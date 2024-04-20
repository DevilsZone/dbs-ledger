package org.dbs.ledger.dto.request.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MobileRequest {
    private String countryCode;

    private String mobileNumber;
}
