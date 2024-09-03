package com.api.kanban_board.services.boards;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveBoardService {

    private final BoardRepository boardRepository;

    public SaveBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardModel execute(BoardModel board){
        return boardRepository.save(board);
    }
}
