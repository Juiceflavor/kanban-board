package com.api.kanban_board.controllers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.mappers.TaskMapper;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.services.Tasks.GetAllTasksService;
import com.api.kanban_board.services.Tasks.GetTaskByIdService;
import com.api.kanban_board.services.Tasks.SaveTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

import static com.api.kanban_board.mappers.TaskMapper.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private SaveTaskService saveTaskService;

    @Autowired
    private GetAllTasksService getAllTasksService;

    @Autowired
    private GetTaskByIdService getTaskByIdService;

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto) {
        TaskModel savedTaskmodel = saveTaskService.execute(toModel(taskDto));
        return new ResponseEntity<>(toDto(savedTaskmodel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskModel> tasks = getAllTasksService.execute();
        return new ResponseEntity<>(toDto(tasks), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long id) {
        TaskModel taskModel = getTaskByIdService.execute(id);
        return new ResponseEntity<>(toDto(taskModel), HttpStatus.OK);
    }

}
