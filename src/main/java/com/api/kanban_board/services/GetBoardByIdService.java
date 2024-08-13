package com.api.kanban_board.services;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class GetBoardByIdService {
    @Autowired
    private BoardRepository boardRepository;

    public BoardEntity execute(Long id){
        return boardRepository.getBoardById(id);
    }
}
