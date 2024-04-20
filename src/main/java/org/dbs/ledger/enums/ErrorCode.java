package org.dbs.ledger.enums;

import lombok.Getter;
import lombok.ToString;
import org.dbs.ledger.util.Constants;

@Getter
@ToString
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("001", "Internal Server Error"),
    INPUT_VALIDATION_ERROR("002", "Input Validation Error"),
    INVALID_JWT_TOKEN("003", "Invalid JWT token provided"),
    JWT_TOKEN_EXPIRED("004", "JWT token expired"),
    TOKEN_MUST_START_WITH_BEARER("005", "Token must start with Bearer"),
    DUPLICATE_ORGANIZATION("006", "Organization already exist"),
    NOT_FOUND("007", "Not Found"),
    DUPLICATE_TEACHER("008", "Teacher already exist"),
    STAFF_ALREADY_EXISTS("009", "Staff already exist"),
    DUPLICATE_BRANCH("010", "Branch already exist"),
    DUPLICATE_INFRASTRUCTURE_SPACE("011", "Infrastructure space already exist"),
    DUPLICATE_ACCOUNT("012", "Account already exists"),
    DUPLICATE_ADMIN("013", "Admin already exists"),
    DUPLICATE_PARENT("014", "Parent already exists"),
    DUPLICATE_FEE_HEAD("015", "Fee head already exists"),
    DUPLICATE_FEE_STRUCTURE("016", "Fee Structure already exists"),
    DUPLICATE_PROGRAM("017", "Program already exists"),
    DUPLICATE_COURSE("018", "Course already exist"),
    DUPLICATE_COURSE_SESSION("019", "Course Session already exists"),
    ORGANIZATION_ACCOUNT_NOT_LINKED("020", "Organization is not owned by the account"),
    DUPLICATE_PROGRAM_SESSION("021", "Program Session already exists");

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = Constants.ERROR_CODE_PREFIX + code;
        this.message = message;
    }
}
