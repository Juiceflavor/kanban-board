package com.api.kanban_board.services.tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTasksByBoardIdService {

    private final TaskRepository taskRepository;

    public GetAllTasksByBoardIdService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> execute(Long board_id){
        return taskRepository.getAllTaskByBoardId(board_id);
    }
}
