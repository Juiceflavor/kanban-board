package com.api.kanban_board.services;

import com.api.kanban_board.models.Tasks;
import com.api.kanban_board.repositories.TasksRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksService {
    @Autowired
    private TasksRepository tasksRepository;

    public Tasks saveTasks(Tasks tasks){
        return tasksRepository.save(tasks);
    }
    public Tasks getTasksById(Long id){
        Optional<Tasks> optionalTasks = tasksRepository.findById(id);
        return optionalTasks.get();
    }
    
    public List<Tasks> getAllTasks(){
        return tasksRepository.findAll();
    }
    
    
    public List<Tasks> getTasksByBoard(Long board_id){
        return tasksRepository.getTasksByBoardId(board_id);
    }
    
    
    public List<Tasks> getTasksByParent(Long parent_id){
        return tasksRepository.getTasksByParentId(parent_id);
    }
}
