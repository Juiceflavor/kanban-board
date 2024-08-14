package com.api.kanban_board.controller;

import com.api.kanban_board.service.BoardService;
import com.api.kanban_board.model.Board;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;
    
    @PostMapping
    public ResponseEntity<?> saveBoard(@RequestBody Board board) {
    try {
            Board savedBoard = boardService.saveBoard(board);
            return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo del error aquí
            return new ResponseEntity<>("Error al guardar el tablero: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
         
    @GetMapping
    public List<Board> getAllBoards(){
        return boardService.getAllBoards();
    }
    
    @GetMapping("{id}")
    public Board searchBoardById(@PathVariable("id") Long id){
        return boardService.getBoardById(id);
    }
}
