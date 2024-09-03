package com.api.kanban_board.services.tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveTaskService {

    private final TaskRepository taskRepository;

    public SaveTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel execute(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }
}
