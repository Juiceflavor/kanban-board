package com.api.kanban_board;

import com.api.kanban_board.models.BoardModel;

public class MockUtils {

    public MockUtils() {}

    public BoardModel makeBoardModelMock() {
        return BoardModel.fromData(1L, "Primer tablero",
                "Primer tablero", "001");
    }
}
