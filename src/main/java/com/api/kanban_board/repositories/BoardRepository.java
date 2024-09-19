package com.api.kanban_board.repositories;

import com.api.kanban_board.models.BoardModel;

import java.util.List;

public interface BoardRepository {

    BoardModel save(BoardModel boardModel);

    BoardModel getBoardById(Integer id);

    List<BoardModel> getAllBoards();

    List<BoardModel> getBoardsByTitle(String title);

    BoardModel transitionBoard(Integer id);

    BoardModel inactive(Integer id);

    BoardModel active(Integer id);
}
