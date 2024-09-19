package com.api.kanban_board.controllers;

import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.services.boards.*;
import com.api.kanban_board.services.tasks.GetAllTasksByBoardIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.api.kanban_board.mappers.BoardMapper.toDto;
import static com.api.kanban_board.mappers.BoardMapper.toDtoList;
import static com.api.kanban_board.mappers.BoardMapper.toModel;
import static com.api.kanban_board.mappers.TaskMapper.toDtoList;

@RestController
@RequestMapping("api/boards")
public class BoardController {

    private final SaveBoardService saveBoardService;
    private final GetBoardByIdService getBoardByIdService;
    private final GetAllBoardsService getAllBoardsService;
    private final GetAllTasksByBoardIdService getAllTasksByBoardIdService;
    private final TransitionBoardService transitionBoardService;
    private final InactiveBoardService inactiveBoardService;
    private final ActiveBoardService activeBoardService;

    public BoardController(SaveBoardService saveBoardService,
                           GetBoardByIdService getBoardByIdService,
                           GetAllBoardsService getAllBoardsService,
                           GetAllTasksByBoardIdService getAllTasksByBoardIdService,
                           TransitionBoardService transitionBoardService,
                           InactiveBoardService inactiveBoardService,
                           ActiveBoardService activeBoardService) {
        this.saveBoardService = saveBoardService;
        this.getBoardByIdService = getBoardByIdService;
        this.getAllBoardsService = getAllBoardsService;
        this.getAllTasksByBoardIdService = getAllTasksByBoardIdService;
        this.transitionBoardService = transitionBoardService;
        this.inactiveBoardService = inactiveBoardService;
        this.activeBoardService = activeBoardService;
    }

    @PostMapping
    public ResponseEntity<BoardDto> saveBoard(@RequestBody BoardDto boardDto) {
        BoardModel savedBoardModel = saveBoardService.execute(toModel(boardDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedBoardModel));
    }

    @GetMapping("{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable("id") Integer id) {
        BoardModel boardModel = getBoardByIdService.execute(id);
        return ResponseEntity.ok(toDto(boardModel));
    }

    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        List<BoardModel> boards = getAllBoardsService.execute();
        return ResponseEntity.ok(toDtoList(boards));
    }

    @GetMapping("/{boardId}/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasksByBoardId(@PathVariable("boardId") Integer boardId) {
        List<TaskModel> tasks = getAllTasksByBoardIdService.execute(boardId);
        return ResponseEntity.ok(toDtoList(tasks));
    }

    @PostMapping("/{id}/transition")
    public ResponseEntity<BoardDto> transitionBoard(@PathVariable("id") Integer id) {
        BoardModel boardModel = transitionBoardService.execute(id);
        return ResponseEntity.ok(toDto(boardModel));
    }

    @PostMapping("/{id}/inactive")
    public ResponseEntity<BoardDto> inactiveBoard(@PathVariable("id") Integer id) {
        BoardModel boardModel = inactiveBoardService.execute(id);
        return ResponseEntity.ok(toDto(boardModel));
    }

    @PostMapping("/{id}/active")
    public ResponseEntity<BoardDto> activeBoard(@PathVariable("id") Integer id) {
        BoardModel boardModel = activeBoardService.execute(id);
        return ResponseEntity.ok(toDto(boardModel));
    }
}
