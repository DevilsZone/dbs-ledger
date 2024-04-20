package org.dbs.ledger.util;

public final class MessageConstants {
    public static final String GLOBAL_EXCEPTION_HANDLER_CAPTURE_MESSAGE = "Exception of type `%s` captured in the GlobalExceptionHandler";

    public static final String VALIDATION_ERROR_MESSAGE_FORMAT = "%s: %s";

    public static final String ENUM_INVALID_CAST_MESSAGE = "%s: must be among %s";

    public static final String REQUIRED_REQUEST_BODY_IS_MISSING = "Required request body is missing";

    public static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE = "MediaType `%s` is not supported. MediaType must be among %s";

    public static final String REQUEST_ID = "requestId";

    public static final String AUTHORIZATION = "Authorization";

    public static final String AUTHORIZATION_HEADER_MUST_BE_PRESENT = "Authorization header must be provided";

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String APPLICATION_JSON = "application/json";

    public static final String BEARER_SPACE = "Bearer ";

    public static final String BEARER_AUTH = "bearerAuth";

    private MessageConstants() {}
}
