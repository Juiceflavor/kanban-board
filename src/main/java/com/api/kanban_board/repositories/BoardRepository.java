package com.api.kanban_board.repositories;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;

import java.util.Optional;

public interface BoardRepository {
    BoardModel save(BoardModel boardModel);

    BoardModel getBoardById(Long id);
}
