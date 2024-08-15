package com.api.kanban_board.controllers;
import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.exceptions.ConflictException;
import com.api.kanban_board.mappers.BoardMapper;
import com.api.kanban_board.models.BoardModel;
import java.util.List;
import java.util.stream.Collectors;

import com.api.kanban_board.services.GetAllBoardsService;
import com.api.kanban_board.services.GetBoardByIdService;
import com.api.kanban_board.services.SaveBoardService;
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
        BoardModel savedBoardModel = saveBoardService.execute(toModel(boardDto));
        return new ResponseEntity<>(toDto(savedBoardModel), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> searchBoardById(@PathVariable("id") Long id) {
        if(id == null || id == 0){
            throw new ConflictException("id is null or 0");
        }
        BoardModel boardModel = getBoardByIdService.execute(id);
        return new ResponseEntity<>(toDto(boardModel), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        List<BoardModel> boards = getAllBoardsService.execute();
        List<BoardDto> boardDtos = boards.stream()
                .map(BoardMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(boardDtos, HttpStatus.OK);
    }
}
