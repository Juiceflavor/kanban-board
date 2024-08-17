package com.api.kanban_board.services;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;

public class SaveBoardService {
    private BoardRepository boardRepository;

    public BoardModel execute(BoardModel board){
        return boardRepository.save(board);
    }
}
