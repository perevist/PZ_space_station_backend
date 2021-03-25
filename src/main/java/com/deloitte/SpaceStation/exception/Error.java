package com.deloitte.SpaceStation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {

    BAD_REQUEST(null, HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_REQUEST(null, HttpStatus.UNAUTHORIZED),
    BAD_LOGIN_CREDENTIALS("Bad login credentials", HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus httpStatus;
}
