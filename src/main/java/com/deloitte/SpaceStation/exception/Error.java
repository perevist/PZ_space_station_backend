package com.deloitte.SpaceStation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {

    BAD_REQUEST(null, HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_REQUEST(null, HttpStatus.UNAUTHORIZED),
    BAD_LOGIN_CREDENTIALS("Bad login credentials", HttpStatus.UNAUTHORIZED),
    USERNAME_ALREADY_EXISTS("Username already exists", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS("Email already exists", HttpStatus.CONFLICT),
    INCORRECT_VERIFICATION_TOKEN("The verification token is incorrect", HttpStatus.BAD_REQUEST),
    VERIFICATION_TOKEN_IS_NOT_VALID("The verification token is not valid", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;
}