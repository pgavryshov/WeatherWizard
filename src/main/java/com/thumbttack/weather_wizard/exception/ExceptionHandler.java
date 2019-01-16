package com.thumbttack.weather_wizard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    protected ResponseEntity<PostgresExceptions> handleForecastException(CustomException e) {
        return new ResponseEntity<>(new PostgresExceptions(e.getErrorText()), HttpStatus.BAD_REQUEST);
    }

}
