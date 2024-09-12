package com.api.kanban_board.models;

import com.api.kanban_board.exceptions.CustomException;
import com.api.kanban_board.exceptions.ExceptionDetails;
import com.api.kanban_board.exceptions.WarningException;
import lombok.Getter;

@Getter
public enum StatusModel {
    INACTIVE("Inactive", "Inactive status", "000"),
    TO_DO("To do", "Initial status", "001"),
    IN_PROGRESS("In progress", "Progress status", "002"),
    DONE("Done", "Finish status", "003");

    private String name;
    private String description;
    private String code;

    StatusModel(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public static StatusModel of(String statusCode) {
        for (StatusModel statusModel : StatusModel.values()) {
            if (statusModel.getCode().equals(statusCode)) {
                return statusModel;
            }
        }
        throw new CustomException("There were a error with the status code",
                new ExceptionDetails("The code " + statusCode + " is not a valid status model",
                        "Error"));
    }

    public StatusModel transition() {
        StatusModel transitioned = null;
        switch (this) {
            case INACTIVE ->
                    throw new CustomException("Cannot transition from " + this.getName(),
                            new ExceptionDetails("Cannot transition from " + this.getName(),
                                    "Warning"));
            case TO_DO -> transitioned = IN_PROGRESS;
            case IN_PROGRESS -> transitioned = DONE;
            case DONE ->
                    throw new CustomException("The " + this.getName() + " has finished",
                            new ExceptionDetails("The " + this.getName() + " has finished",
                                    "Warning"));
        }

        return transitioned;
    }

    public StatusModel inactive() {
        if (this.equals(StatusModel.INACTIVE)) {
            throw new CustomException("It cannot be inactivated, already in this state",
                    new ExceptionDetails("It cannot be inactivated, already in this state",
                            "Warning"));
        }
        if (this.equals(StatusModel.DONE)) {
            throw new CustomException("It cannot be inactivated, it was already done",
                    new ExceptionDetails("It cannot be inactivated, it was already done",
                            "Warning"));
        }
        return StatusModel.INACTIVE;
    }

    public StatusModel active() {
        if (!this.equals(StatusModel.INACTIVE)) {
            throw new CustomException("It cannot be activated, isn't in the status Inactive",
                    new ExceptionDetails("It cannot be activated, isn't in the status Inactive",
                            "Warning"));
        }
        return StatusModel.TO_DO;
    }
}
