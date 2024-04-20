package org.dbs.ledger.exceptions;

import lombok.Getter;
import org.dbs.ledger.dto.response.wrapper.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class RestException extends RuntimeException {
    private final HttpStatus httpStatus;

    private final List<ErrorResponse> errorResponses;

    public RestException(HttpStatus httpStatus, ErrorResponse errorResponse) {
        this(httpStatus, errorResponse, null);
    }

    public RestException(HttpStatus httpStatus, ErrorResponse errorResponse, Throwable cause) {
        this(httpStatus, List.of(errorResponse), cause);
    }

    public RestException(HttpStatus httpStatus, List<ErrorResponse> errorResponses, Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
        this.errorResponses = errorResponses;
    }
}
