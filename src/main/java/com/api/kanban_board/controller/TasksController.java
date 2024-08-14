package com.api.kanban_board.controller;

import com.api.kanban_board.model.Tasks;
import com.api.kanban_board.service.TasksService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks")
public class TasksController {
    @Autowired
    private TasksService tasksService;
    
    @PostMapping
    public Tasks saveTasks(@RequestBody Tasks tasks){
        return tasksService.saveTasks(tasks);
    }
    
    @GetMapping
    public List<Tasks> getAllTasks(){
        return tasksService.getAllTasks();
    }
    
    @GetMapping("{id}")
    public Tasks searchTasksById(@PathVariable("id") Long id){
        return tasksService.getTasksById(id);
    }
    
    @GetMapping("/board/{board_id}")
    public List<Tasks> searchTasksByBoardId(@PathVariable("board_id") Long board_id){
        return tasksService.getTasksByBoard(board_id);
    }
    
    @GetMapping("/parent/{parent_id}")
    public List<Tasks> searchTasksByParentId(@PathVariable("parent_id") Long parent_id){
        return tasksService.getTasksByParent(parent_id);
    }
}
