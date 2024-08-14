package com.api.kanban_board.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "boards")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;

    public BoardEntity(){
    }

    public BoardEntity(Long id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public BoardEntity(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.status = builder.status;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private String status;

        public BoardEntity.Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public BoardEntity.Builder Title(String title) {
            this.title = title;
            return this;
        }

        public BoardEntity.Builder Description(String description) {
            this.description = description;
            return this;
        }

        public BoardEntity.Builder Status(String status) {
            this.status = status;
            return this;
        }

        public BoardEntity build() {
            return new BoardEntity(this);
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

    public String getStatus() {
        return status;
    }
}
