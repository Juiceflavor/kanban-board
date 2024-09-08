package com.api.kanban_board.services.tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTasksService {

    private final TaskRepository taskRepository;

    public GetAllTasksService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> execute(){
        return taskRepository.getAllTasks();
    }
}
