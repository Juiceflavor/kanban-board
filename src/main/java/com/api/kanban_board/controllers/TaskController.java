package com.api.kanban_board.controllers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.services.Tasks.GetAllTasksByBoardIdService;
import com.api.kanban_board.services.Tasks.SaveTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.api.kanban_board.mappers.TaskMapper.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private SaveTaskService saveTaskService;
    @Autowired
    private GetAllTasksByBoardIdService getAllTasksByBoardIdService;

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto) {
        TaskModel savedTaskmodel = saveTaskService.execute(toModel(taskDto));
        return new ResponseEntity<>(toDto(savedTaskmodel), HttpStatus.CREATED);
    }

    @GetMapping("board_id/{board_id}")
    public ResponseEntity<List<TaskDto>> getAllTasksByBoardId(@PathVariable("board_id") Long board_id) {
        List<TaskModel> tasks = getAllTasksByBoardIdService.execute(board_id);
        List<TaskDto> tasksDtos = tasks.stream().map(model -> toDto(model)).collect(Collectors.toList());
        return new ResponseEntity<>(tasksDtos, HttpStatus.OK);
    }
}
