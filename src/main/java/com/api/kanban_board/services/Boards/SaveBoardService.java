package com.api.kanban_board.services.Boards;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveBoardService {
    @Autowired
    private BoardRepository boardRepository;

    public BoardModel execute(BoardModel board){
        return boardRepository.save(board);
    }
}
