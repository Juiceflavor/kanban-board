package com.api.kanban_board.services.tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class ActiveTaskService {

    private final TaskRepository taskRepository;

    public ActiveTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel execute(Integer id) {
        return taskRepository.activeTask(id);
    }
}
