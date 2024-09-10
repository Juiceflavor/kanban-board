package com.api.kanban_board.models;

import com.api.kanban_board.exceptions.ConflictException;
import com.api.kanban_board.models.utils.IntegerUtils;
import com.api.kanban_board.models.utils.StringUtils;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TaskModel {
    private Integer id;
    private String name;
    private StatusModel status;
    private String description;
    private Integer parentId;
    private Integer boardId;

    private TaskModel(Integer id, String name, String statusCode, String description, Integer parentId, Integer boardId) {
        validateBoard_id(boardId);
        validateDescription(description);
        validateStatus(statusCode);
        validateId(id);

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
        validateParent_id(parentId);
        validateBoard_id(boardId);
        
        this.name = name;
        this.status = StatusModel.of(statusCode);
        this.description = description;
        this.parentId = parentId;
        this.boardId = boardId;
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

    private void validateBoard_id(Integer boardId) {
        IntegerUtils.validateNullAndNegative("boardId", boardId);
    }

    private void validateName(String name) {
        StringUtils.validateNullAndEmpty("name", name);
    }

    private void validateParent_id(Integer parentId) {
        IntegerUtils.validateNullAndNegative("parentId", parentId);
    }

    public static TaskModel create(String name, String description, Integer parentId, Integer boardId) {
        return new TaskModel(name, StatusModel.TO_DO.getCode(), description, parentId, boardId);
    }

    public static TaskModel fromData(Integer id, String name, String status, String description, Integer parentId, Integer boardId) {
        return new TaskModel(id, name, status, description, parentId, boardId);
    }

    public TaskModel transition(){
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
