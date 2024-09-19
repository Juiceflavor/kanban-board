package com.api.kanban_board.controllers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.services.tasks.*;
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
    private final GetAllTaskByParentIdService getAllTaskByParentIdService;
    private final TransitionTaskService transitionTaskService;
    private final InactiveTaskService inactiveTaskService;
    private final ActiveTaskService activeTaskService;

    public TaskController(SaveTaskService saveTaskService,
                          GetAllTasksService getAllTasksService,
                          GetTaskByIdService getTaskByIdService,
                          GetAllTaskByParentIdService getAllTaskByParentIdService,
                          TransitionTaskService transitionTaskService,
                          InactiveTaskService inactiveTaskService,
                          ActiveTaskService activeTaskService) {
        this.saveTaskService = saveTaskService;
        this.getAllTasksService = getAllTasksService;
        this.getTaskByIdService = getTaskByIdService;
        this.getAllTaskByParentIdService = getAllTaskByParentIdService;
        this.transitionTaskService = transitionTaskService;
        this.inactiveTaskService = inactiveTaskService;
        this.activeTaskService = activeTaskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) {
        TaskModel savedTaskmodel = saveTaskService.execute(toModel(taskDto));
        return ResponseEntity.ok(toDto(savedTaskmodel));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskModel> tasks = getAllTasksService.execute();
        return ResponseEntity.ok(toDtoList(tasks));
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Integer id) {
        TaskModel taskModel = getTaskByIdService.execute(id);
        return ResponseEntity.ok(toDto(taskModel));
    }

    @GetMapping("{id}/children_tasks")
    public ResponseEntity<List<TaskDto>> getTaskByParentId(@PathVariable("id") Integer id) {
        List<TaskModel> tasksModel = getAllTaskByParentIdService.execute(id);
        return ResponseEntity.ok(toDtoList(tasksModel));
    }

    @PostMapping("/{id}/transition")
    public ResponseEntity<TaskDto> transitionTask(@PathVariable("id") Integer id) {
        TaskModel taskModel = transitionTaskService.execute(id);
        return ResponseEntity.ok(toDto(taskModel));
    }

    @PostMapping("/{id}/inactive")
    public ResponseEntity<TaskDto> inactiveTask(@PathVariable("id") Integer id) {
        TaskModel taskModel = inactiveTaskService.execute(id);
        return ResponseEntity.ok(toDto(taskModel));
    }

    @PostMapping("/{id}/active")
    public ResponseEntity<TaskDto> activeTask(@PathVariable("id") Integer id) {
        TaskModel taskModel = activeTaskService.execute(id);
        return ResponseEntity.ok(toDto(taskModel));
    }
}
