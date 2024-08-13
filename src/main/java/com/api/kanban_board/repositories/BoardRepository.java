package com.api.kanban_board.repositories;

import com.api.kanban_board.models.BoardModel;

public interface BoardRepository {
    BoardModel save(BoardModel boardModel);
}