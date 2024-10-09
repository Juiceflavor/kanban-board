package com.api.kanban_board.services.tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTaskByParentIdService {

    private final TaskRepository taskRepository;

    public GetAllTaskByParentIdService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> execute(Integer parentId) {
        return taskRepository.getAllTaskByParentId(parentId);
    }
}
