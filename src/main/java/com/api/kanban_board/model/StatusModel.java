package com.api.kanban_board.model;

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
        throw new RuntimeException("Invalid status code: " + statusCode);
    }

    public StatusModel transition() {
        StatusModel transitioned = null;
        switch (this) {
            case INACTIVE -> throw new RuntimeException("Cannot transition from " + this.getName());
            case TO_DO -> transitioned = IN_PROGRESS;
            case IN_PROGRESS -> transitioned = DONE;
            case DONE -> throw new RuntimeException("The " + this.getName() + " has finished");
        }

        return transitioned;
    }

    public StatusModel inactive() {
        if (this.equals(StatusModel.INACTIVE)) {
            throw new RuntimeException("task");
        }
        return StatusModel.INACTIVE;
    }

    public StatusModel active() {
        if (!this.equals(StatusModel.INACTIVE)) {
            throw new RuntimeException("task");
        }
        return StatusModel.TO_DO;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}
