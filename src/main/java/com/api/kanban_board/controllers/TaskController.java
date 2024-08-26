package com.api.kanban_board.controllers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.mappers.TaskMapper;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.services.Tasks.GetAllTasksService;
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
    private GetAllTasksService getAllTasksService;

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto) {
        TaskModel savedTaskmodel = saveTaskService.execute(toModel(taskDto));
        return new ResponseEntity<>(toDto(savedTaskmodel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskModel> tasks = getAllTasksService.execute();
        List<TaskDto> tasksDtos = tasks.stream().map(TaskMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(tasksDtos, HttpStatus.OK);
    }
}
