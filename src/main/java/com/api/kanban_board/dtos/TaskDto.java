package com.api.kanban_board.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TaskDto {
    private Integer id;
    private String name;
    private String statusCode;
    private String description;
    private Integer parentId;
    private Integer boardId;
}
