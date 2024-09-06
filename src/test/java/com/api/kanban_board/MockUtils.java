package com.api.kanban_board;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.models.TaskModel;

public class MockUtils {

    public MockUtils() {}

    public BoardModel makeBoardModelMock() {
        return BoardModel.fromData(1L, "Primer tablero",
                "Primer tablero", "001");
    }

    public TaskModel makeTaskModelMock() {
        return TaskModel.fromData(1L, "Primera tarea",
                "001", "Primera tarea", null, 1L);
    }
}
