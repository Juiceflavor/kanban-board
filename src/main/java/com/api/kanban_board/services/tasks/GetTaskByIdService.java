package com.api.kanban_board.services.tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class GetTaskByIdService {

    private final TaskRepository taskRepository;

    public GetTaskByIdService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel execute(Integer id){
        return taskRepository.getTaskById(id);
    }
}
