package com.api.kanban_board.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String status;
    private String description;
    private Integer parentId;
    private Integer boardId;
}
