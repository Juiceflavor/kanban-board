package com.api.kanban_board;

import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.models.TaskModel;

public final class MockUtils {

    private MockUtils() {}

    public static BoardModel makeBoardModelMock() {
        return BoardModel.fromData(1, "Primer tablero",
                "Primer tablero", "001");
    }

    public static BoardEntity makeBoardEntityMock() {
        return BoardEntity.builder().id(1).title("Primer tablero")
                .description("Primer tablero").status("001").build();
    }

    public static BoardDto makeBoardDtoMock() {
        return BoardDto.builder().id(1).title("Primer tablero")
                .description("Primer tablero").statusCode("001").build();
    }

    public static TaskModel makeTaskModelMock() {
        return TaskModel.fromData(1, "Primera tarea",
                "001", "Primera tarea", null, 1);
    }

    public static TaskEntity makeTaskEntityMock() {
        return TaskEntity.builder().id(1).name("Primera tarea").status("001")
                .description("Primera tarea").boardId(1).build();
    }

    public static TaskDto makeTaskDtoMock() {
        return TaskDto.builder().id(1).name("Primera tarea").statusCode("001")
                .description("Primera tarea").boardId(1).build();
    }
}
