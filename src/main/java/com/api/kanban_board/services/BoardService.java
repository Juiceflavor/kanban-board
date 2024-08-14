package com.api.kanban_board.services;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    
    public BoardModel saveBoard(BoardModel board){
        return boardRepository.save(board);
    }
    
    public BoardModel getBoardById(Long id){ return boardRepository.getBoardById(id); }
}
