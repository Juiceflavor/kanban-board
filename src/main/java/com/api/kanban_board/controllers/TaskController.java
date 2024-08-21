package com.api.kanban_board.controllers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.services.Tasks.SaveTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api.kanban_board.mappers.TaskMapper.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private SaveTaskService saveTaskService;

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto) {
        TaskModel savedTaskmodel = saveTaskService.execute(toModel(taskDto));
        return new ResponseEntity<>(toDto(savedTaskmodel), HttpStatus.CREATED);
    }

}
