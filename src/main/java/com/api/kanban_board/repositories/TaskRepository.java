package com.api.kanban_board.repositories;

import com.api.kanban_board.models.TaskModel;


public interface TaskRepository {

    TaskModel save(TaskModel taskModel);

    TaskModel getTaskById(Long id);
}
