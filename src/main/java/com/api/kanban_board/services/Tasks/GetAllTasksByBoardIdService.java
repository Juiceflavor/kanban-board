package com.api.kanban_board.services.Tasks;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTasksByBoardIdService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskModel> execute(Long board_id){
        return taskRepository.getAllTaskByBoardId(board_id);
    }
}
