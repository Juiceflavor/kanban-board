package com.api.kanban_board;

import com.api.kanban_board.models.BoardModel;
import org.springframework.stereotype.Component;

@Component
public final class MockUtils {

    public MockUtils() {}

    public static BoardModel makeBoardModelMock() {
        return BoardModel.fromData(1L, "Primer tablero",
                "Primer tablero", "001");
    }
}
