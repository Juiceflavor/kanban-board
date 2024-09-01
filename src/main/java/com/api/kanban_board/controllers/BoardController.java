package com.api.kanban_board.controllers;
import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.models.BoardModel;
import java.util.List;

import com.api.kanban_board.services.Boards.GetAllBoardsService;
import com.api.kanban_board.services.Boards.GetBoardByIdService;
import com.api.kanban_board.services.Boards.SaveBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api.kanban_board.mappers.BoardMapper.*;

@RestController
@RequestMapping("api/boards")
public class BoardController {

    @Autowired
    private SaveBoardService saveBoardService;

    @Autowired
    private GetBoardByIdService getBoardByIdService;

    @Autowired
    private GetAllBoardsService getAllBoardsService;

    @PostMapping
    public ResponseEntity<?> saveBoard(@RequestBody BoardDto boardDto) {
        BoardModel savedBoardModel = saveBoardService.execute(toModelList(boardDto));
        return new ResponseEntity<>(toDtoList(savedBoardModel), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBoardById(@PathVariable("id") Long id) {
        BoardModel boardModel = getBoardByIdService.execute(id);
        return new ResponseEntity<>(toDtoList(boardModel), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        List<BoardModel> boards = getAllBoardsService.execute();
        return new ResponseEntity<>(toDtoList(boards), HttpStatus.OK);
    }
}
