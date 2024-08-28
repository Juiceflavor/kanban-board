package com.api.kanban_board.repositories;

import com.api.kanban_board.models.TaskModel;

import java.util.List;


public interface TaskRepository {

    TaskModel save(TaskModel taskModel);

    List<TaskModel> getAllTaskByBoardId(Long board_id);
}
