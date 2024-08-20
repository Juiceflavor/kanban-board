package com.api.kanban_board.models;

import com.api.kanban_board.exceptions.ConflictException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TasksModel {
    private Long id;
    private String name;
    private StatusModel status;
    private String description;
    private Long parent_id;
    private Long board_id;

    private TasksModel(Long id, String name, String statusCode, String description, Long parent_id, Long board_id) {
        validateBoard_id(board_id);
        validateDescription(description);
        validateStatus(statusCode);
        validateId(id);

        this.id = id;
        this.name = name;
        this.status = StatusModel.of(statusCode);
        this.description = description;
        this.parent_id = parent_id;
        this.board_id = board_id;
    }

    private TasksModel(String name, String statusCode, String description, Long parent_id, Long board_id) {
        validateBoard_id(board_id);
        validateDescription(description);
        validateStatus(statusCode);

        this.name = name;
        this.status = StatusModel.of(statusCode);
        this.description = description;
        this.parent_id = parent_id;
        this.board_id = board_id;
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new ConflictException("Error the id is null or empty");
        }
    }

    private void validateStatus(String statusCode) {
        if (statusCode == null) {
            throw new ConflictException("Error the status is null or empty");
        }
    }

    private void validateDescription(String description) {
        if (description == null) {
            throw new ConflictException("Error the description is null or empty");
        }
    }

    private void validateBoard_id(Long board_id) {
        if (board_id == null) {
            throw new ConflictException("Error the board_id is null or empty");
        }
    }

    public static TasksModel create(String name, String description, Long parent_id, Long board_id) {
        return new TasksModel(0L, name,"To do", description, parent_id, board_id);
    }

    public static TasksModel fromData(Long id, String name, String status, String description, Long parent_id, Long board_id) {
        return new TasksModel(id, name, status, description, parent_id, board_id);
    }

    public TasksModel transition(){
        this.status = this.status.transition();

        return fromData(this.id, this.name, this.status.getCode(), this.description, this.parent_id, this.board_id);
    }

    public TasksModel inactive() {
        this.status = this.status.inactive();

        return fromData(this.id, this.name, this.status.getCode(), this.description, this.parent_id, this.board_id);
    }

    public TasksModel active() {
        this.status = this.status.active();

        return fromData(this.id, this.name, this.status.getCode(), this.description, this.parent_id, this.board_id);
    }
}
