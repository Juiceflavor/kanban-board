package com.api.kanban_board.controllers;
import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.models.BoardModel;
import java.util.List;

import com.api.kanban_board.services.boards.GetAllBoardsService;
import com.api.kanban_board.services.boards.GetBoardByIdService;
import com.api.kanban_board.services.boards.SaveBoardService;
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

    private final SaveBoardService saveBoardService;
    private final GetBoardByIdService getBoardByIdService;
    private final GetAllBoardsService getAllBoardsService;

    public BoardController(SaveBoardService saveBoardService,
                           GetBoardByIdService getBoardByIdService,
                           GetAllBoardsService getAllBoardsService) {
        this.saveBoardService = saveBoardService;
        this.getBoardByIdService = getBoardByIdService;
        this.getAllBoardsService = getAllBoardsService;
    }

    @PostMapping
    public ResponseEntity<?> saveBoard(@RequestBody BoardDto boardDto) {
        BoardModel savedBoardModel = saveBoardService.execute(toModel(boardDto));
        return new ResponseEntity<>(toDto(savedBoardModel), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBoardById(@PathVariable("id") Long id) {
        BoardModel boardModel = getBoardByIdService.execute(id);
        return new ResponseEntity<>(toDto(boardModel), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        List<BoardModel> boards = getAllBoardsService.execute();
        return new ResponseEntity<>(toDtoList(boards), HttpStatus.OK);
    }
}
