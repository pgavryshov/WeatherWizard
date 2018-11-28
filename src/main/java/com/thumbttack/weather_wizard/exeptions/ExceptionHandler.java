package com.thumbttack.weather_wizard.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IsForecastCopyException.class)
    protected ResponseEntity<PostgressExeptions> handleForecastException(IsForecastCopyException e) {
        return new ResponseEntity<>(new PostgressExeptions(e.getErrorText()), HttpStatus.BAD_REQUEST);
    }

}
