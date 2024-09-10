package com.api.kanban_board.services.tasks;

import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.exceptions.WarningException;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveTaskService {

    private final TaskRepository taskRepository;

    public SaveTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel execute(TaskModel taskModel){
        TaskModel task = null;
        if(taskRepository.getTasksByName(taskModel.getName()).isEmpty()){
            task = taskRepository.save(taskModel);
        } else {
            throw new WarningException("The name of the task already exists");
        }
        return task;
    }
}
