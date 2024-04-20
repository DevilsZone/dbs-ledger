package org.dbs.ledger.dto.request.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.dbs.ledger.dto.request.common.EmailRequest;

@Getter
@Setter
@SuperBuilder
public final class EmailSignInRequest {
    @Valid
    @NotNull
    private EmailRequest emailRequest;

    @NotBlank
    private String password;
}
