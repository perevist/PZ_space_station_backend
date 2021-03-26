package com.deloitte.SpaceStation.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = SpaceStationException.class)
    public ResponseEntity<ErrorInfo> handleException(SpaceStationException exception) {

        HttpStatus httpStatus = exception.getError().getHttpStatus();
        String message = exception.getError().getMessage();

        if (message != null) {
            return ResponseEntity.status(httpStatus)
                    .body(new ErrorInfo(exception.getError().getMessage()));
        } else {
            return ResponseEntity.status(httpStatus).build();
        }
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorInfo> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errors = result.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorInfo(errors));
    }
}
