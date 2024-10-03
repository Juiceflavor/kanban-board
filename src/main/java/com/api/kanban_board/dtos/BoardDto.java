package com.api.kanban_board.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardDto {
    private Integer id;
    private String title;
    private String description;
    private String statusCode;
}
