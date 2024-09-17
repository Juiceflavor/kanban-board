package com.api.kanban_board.models;
import com.api.kanban_board.exceptions.ConflictException;
import com.api.kanban_board.models.utils.IntegerUtils;
import com.api.kanban_board.models.utils.StringUtils;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardModel {
    private Integer id;
    private String title;
    private String description;
    private StatusModel status;

    private BoardModel(Integer id, String title, String description, String statusCode)  {
        validateId(id);
        validateTitle(title);
        validateDescription(description);
        validateStatus(statusCode);

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = StatusModel.of(statusCode);
    }

    private BoardModel(String title, String description, String statusCode)  {
        validateTitle(title);
        validateDescription(description);
        validateStatus(statusCode);

        this.title = title;
        this.description = description;
        this.status = StatusModel.of(statusCode);
    }

    private void validateId(Integer id)  {
        IntegerUtils.validateNullAndNegative("id", id);
    }

    private void validateStatus(String status)  {
        StringUtils.validateNullAndEmpty("status", status);
    }

    private void validateDescription(String description)  {
        StringUtils.validateNullAndEmpty("description", description);
    }

    private void validateTitle(String title)  {
        StringUtils.validateNullAndEmpty("title", title);
    }

    public static BoardModel create(String title, String description)  {
        return new BoardModel(title, description, StatusModel.TO_DO.getCode());
    }

    public static BoardModel fromData(Integer id, String title, String description, String status)  {
        return new BoardModel(id, title, description, status);
    }

    public BoardModel transition()  {
        this.status = this.status.transition();

        return fromData(this.id, this.title, this.description, this.status.getCode());
    }

    public BoardModel inactive()  {
        this.status = this.status.inactive();

        return fromData(this.id, this.title, this.description, this.status.getCode());
    }

    public BoardModel active()  {
        this.status = this.status.active();

        return fromData(this.id, this.title, this.description, this.status.getCode());
    }
}
