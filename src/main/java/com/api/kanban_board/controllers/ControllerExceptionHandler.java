package com.api.kanban_board.controllers;

import com.api.kanban_board.exceptions.CustomException;
import com.api.kanban_board.exceptions.ExceptionDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class.getName());

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ExceptionDetails> handleException(CustomException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ExceptionDetails> handleException(Throwable exception) {
        LOG.error(exception.getMessage(), exception);
        var details = new ExceptionDetails("An unexpected error has occurred", "Error");
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
