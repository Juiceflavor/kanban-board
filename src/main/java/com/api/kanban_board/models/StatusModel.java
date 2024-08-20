package com.api.kanban_board.models;
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
        throw new WarningException("The code " + statusCode + " is not a valid status model");
    }

    public StatusModel transition() {
        StatusModel transitioned = null;
        switch (this) {
            case INACTIVE -> throw new WarningException("Cannot transition from " + this.getName());
            case TO_DO -> transitioned = IN_PROGRESS;
            case IN_PROGRESS -> transitioned = DONE;
            case DONE -> throw new WarningException("The " + this.getName() + " has finished");
        }

        return transitioned;
    }

    public StatusModel inactive() {
        if (this.equals(StatusModel.INACTIVE)) {
            throw new WarningException("It cannot be inactivated, already in this state");
        }
        if (this.equals(StatusModel.DONE)) {
            throw new WarningException("It cannot be inactivated, it was already done");
        }
        return StatusModel.INACTIVE;
    }

    public StatusModel active() {
        if (!this.equals(StatusModel.INACTIVE)) {
            throw new WarningException("It cannot be activated, isn't in the status Inactive");
        }
        return StatusModel.TO_DO;
    }
}
