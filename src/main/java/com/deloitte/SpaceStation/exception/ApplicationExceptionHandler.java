package com.deloitte.SpaceStation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = SpaceStationException.class)
    public ResponseEntity<ErrorInfo> handleException(SpaceStationException exception) {

        HttpStatus httpStatus = exception.getError().getHttpStatus();
        String message = exception.getError().getMessage();

        if(message != null) {
            return ResponseEntity.status(httpStatus)
                    .body(new ErrorInfo(exception.getError().getMessage()));
        }
        else {
            return ResponseEntity.status(httpStatus).build();
        }
    }
}
