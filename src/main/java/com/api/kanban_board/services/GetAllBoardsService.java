package com.api.kanban_board.services;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBoardsService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardModel> execute(){
        return boardRepository.getAllBoards();
    }
}
