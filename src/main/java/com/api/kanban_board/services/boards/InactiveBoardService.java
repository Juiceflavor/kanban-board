package com.api.kanban_board.services.boards;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.stereotype.Component;

@Component
public class InactiveBoardService {

    private final BoardRepository boardRepository;

    public InactiveBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardModel execute(Integer id){
        return boardRepository.inactive(id);
    }
}
