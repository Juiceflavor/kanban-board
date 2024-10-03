package com.api.kanban_board.services.boards;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBoardsService {

    private final BoardRepository boardRepository;

    public GetAllBoardsService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardModel> execute(){
        return boardRepository.getAllBoards();
    }
}
