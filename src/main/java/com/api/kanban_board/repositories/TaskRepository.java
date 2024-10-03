package com.api.kanban_board.repositories;

import com.api.kanban_board.models.TaskModel;

import java.util.List;


public interface TaskRepository {

    TaskModel save(TaskModel taskModel);

    List<TaskModel> getAllTaskByBoardId(Integer boardId);

    List<TaskModel> getAllTaskByParentId(Integer parentId);

    List<TaskModel> getAllTasks();

    TaskModel getTaskById(Integer id);

    List<TaskModel> getTasksByName(String name);

    TaskModel transitionTask(Integer id);

    TaskModel inactiveTask(Integer id);

    TaskModel activeTask(Integer id);
}
