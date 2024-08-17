package com.api.kanban_board.dtos;

public class BoardDto {
    private Long id;
    private String title;
    private String description;
    private String statusCode;

    public BoardDto() {
    }

    public BoardDto(Long id, String title, String description, String statusCode) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.statusCode = statusCode;
    }

    public BoardDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.statusCode = builder.statusCode;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private String statusCode;

        public BoardDto.Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public BoardDto.Builder Title(String title) {
            this.title = title;
            return this;
        }

        public BoardDto.Builder Description(String description) {
            this.description = description;
            return this;
        }

        public BoardDto.Builder StatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public BoardDto build() {
            return new BoardDto(this);
        }
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

    public String getStatusCode() {
        return statusCode;
    }
}
