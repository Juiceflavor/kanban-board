package com.api.kanban_board.controllers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.services.tasks.GetAllTasksByBoardIdService;
import com.api.kanban_board.services.tasks.GetAllTasksService;
import com.api.kanban_board.services.tasks.GetTaskByIdService;
import com.api.kanban_board.services.tasks.SaveTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.api.kanban_board.mappers.TaskMapper.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final SaveTaskService saveTaskService;
    private final GetAllTasksService getAllTasksService;
    private final GetTaskByIdService getTaskByIdService;

    public TaskController(SaveTaskService saveTaskService,
                          GetAllTasksService getAllTasksService,
                          GetTaskByIdService getTaskByIdService){
        this.saveTaskService = saveTaskService;
        this.getAllTasksService = getAllTasksService;
        this.getTaskByIdService = getTaskByIdService;
    }

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto) {
        TaskModel savedTaskmodel = saveTaskService.execute(toModel(taskDto));
        return new ResponseEntity<>(toDto(savedTaskmodel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskModel> tasks = getAllTasksService.execute();
        return new ResponseEntity<>(toDtoList(tasks), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Integer id) {
        TaskModel taskModel = getTaskByIdService.execute(id);
        return new ResponseEntity<>(toDto(taskModel), HttpStatus.OK);
    }
}
