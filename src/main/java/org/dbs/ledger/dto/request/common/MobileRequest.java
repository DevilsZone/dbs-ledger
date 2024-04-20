package org.dbs.ledger.dto.request.common;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MobileRequest {
    @NotBlank
    private String countryCode;

    @NotBlank
    private String mobileNumber;
}
