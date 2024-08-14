
package com.api.kanban_board.service;

import com.api.kanban_board.model.Board;
import com.api.kanban_board.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    
    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }
    
    public Board getBoardById(Long id){
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.get();
    }
    
    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }
}
