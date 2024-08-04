package com.api.kanban_board.model;

public class BoardModel {
    private Long id;
    private String title;
    private String description;
    private StatusModel status;

    private BoardModel(Long id, String title, String description, String statusCode)  {
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

    private void validateId(Long id)  {
        if (id == null) {
            throw new RuntimeException("id");
        }
    }

    private void validateStatus(String status)  {
        if (status == null || status.isEmpty()) {
            throw new RuntimeException("status");
        }
    }

    private void validateDescription(String description)  {
        if (description == null || description.isEmpty()) {
            throw new RuntimeException("description");
        }
    }

    private void validateTitle(String title)  {
        if (title == null || title.isEmpty()) {
            throw new RuntimeException("title");
        }
    }

    public static BoardModel create(String title, String description)  {
        return new BoardModel(title, description, "To do");
    }

    public static BoardModel fromData(long id, String title, String description, String status)  {
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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public StatusModel getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "BoardModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
