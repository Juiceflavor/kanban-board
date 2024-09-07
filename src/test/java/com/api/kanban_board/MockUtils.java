package com.api.kanban_board;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.models.TaskModel;

public class MockUtils {

    public MockUtils() {}

    public BoardModel makeBoardModelMock() {
        return BoardModel.fromData(1L, "Primer tablero",
                "Primer tablero", "001");
    }

    public BoardEntity makeBoardEntityMock() {
        return BoardEntity.builder().id(1L).title("Primer tablero")
                .description("Primer tablero").status("001").build();
    }

    public TaskModel makeTaskModelMock() {
        return TaskModel.fromData(1L, "Primera tarea",
                "001", "Primera tarea", null, 1L);
    }

    public TaskEntity makeTaskEntityMock() {
        return TaskEntity.builder().id(1L).name("Primera tarea").status("001")
                .description("Primera tarea").board_id(1L).build();
    }
}
