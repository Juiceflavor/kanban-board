package com.api.kanban_board.services.Tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskModel execute(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }
}
