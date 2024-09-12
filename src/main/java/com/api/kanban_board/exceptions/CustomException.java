package com.api.kanban_board.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private ExceptionDetails details;

    public CustomException(String message, ExceptionDetails details, Throwable e) {
        super(message, e);
        setDetails(details);
    }

    public CustomException(String message, ExceptionDetails details) {
        super(message);
        setDetails(details);
    }
}
