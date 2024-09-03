package com.api.kanban_board.services.Tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetTaskByIdService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskModel execute(Long id){
        return taskRepository.getTaskById(id);
    }
}
