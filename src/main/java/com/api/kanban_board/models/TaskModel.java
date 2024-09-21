package com.api.kanban_board.models;

import com.api.kanban_board.exceptions.WarningException;
import com.api.kanban_board.models.utils.IntegerUtils;
import com.api.kanban_board.models.utils.StringUtils;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TaskModel {
    private Integer id;
    private final String name;
    private StatusModel status;
    private final String description;
    private final Integer parentId;
    private final Integer boardId;

    private TaskModel(Integer id, String name, String statusCode, String description, Integer parentId,
                      Integer boardId) {
        validateId(id);
        validateName(name);
        validateStatus(statusCode);
        validateDescription(description);
        validateParentId(parentId);
        validateBoardId(boardId);

        this.id = id;
        this.name = name;
        this.status = StatusModel.of(statusCode);
        this.description = description;
        this.parentId = parentId;
        this.boardId = boardId;
    }

    private TaskModel(String name, String statusCode, String description, Integer parentId, Integer boardId) {
        validateName(name);
        validateStatus(statusCode);
        validateDescription(description);
        validateParentId(parentId);
        validateBoardId(boardId);

        this.name = name;
        this.status = StatusModel.of(statusCode);
        this.description = description;
        this.parentId = parentId;
        this.boardId = boardId;
    }

    public static TaskModel create(String name, String description, Integer parentId, Integer boardId) {
        return new TaskModel(name, StatusModel.TO_DO.getCode(), description, parentId, boardId);
    }

    public static TaskModel fromData(Integer id, String name, String status, String description, Integer parentId,
                                     Integer boardId) {
        return new TaskModel(id, name, status, description, parentId, boardId);
    }

    private void validateId(Integer id) {
        IntegerUtils.validateNullAndNegative("Id", id);
    }

    private void validateStatus(String statusCode) {
        StringUtils.validateNullAndEmpty("statusCode", statusCode);
    }

    private void validateDescription(String description) {
        StringUtils.validateNullAndEmpty("description", description);
    }

    private void validateBoardId(Integer boardId) {
        if(boardId != null && boardId <= 0) {
            throw new WarningException("Parent id must be greater than zero");
        }
    }

    private void validateName(String name) {
        StringUtils.validateNullAndEmpty("name", name);
    }

    private void validateParentId(Integer parentId) {
        if(parentId != null && parentId <= 0) {
            throw new WarningException("Parent id must be greater than zero");
        }
    }

    public TaskModel transition() {
        this.status = this.status.transition();

        return fromData(this.id, this.name, this.status.getCode(), this.description, this.parentId, this.boardId);
    }

    public TaskModel inactive() {
        this.status = this.status.inactive();

        return fromData(this.id, this.name, this.status.getCode(), this.description, this.parentId, this.boardId);
    }

    public TaskModel active() {
        this.status = this.status.active();

        return fromData(this.id, this.name, this.status.getCode(), this.description, this.parentId, this.boardId);
    }
}
