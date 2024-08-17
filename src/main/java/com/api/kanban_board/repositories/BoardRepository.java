package com.api.kanban_board.repositories;

import com.api.kanban_board.models.BoardModel;

import java.util.List;

public interface BoardRepository {
    BoardModel save(BoardModel boardModel);

    BoardModel getBoardById(Long id);

    List<BoardModel> getAllBoards();
}
