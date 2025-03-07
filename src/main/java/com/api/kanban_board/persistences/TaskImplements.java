package com.api.kanban_board.persistences;

import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.api.kanban_board.mappers.TaskMapper.*;

@Component
public class TaskImplements implements TaskRepository {

    private final TaskJpaRepositoryAdapter taskJpaRepositoryAdapter;

    public TaskImplements(TaskJpaRepositoryAdapter taskJpaRepositoryAdapter) {
        this.taskJpaRepositoryAdapter = taskJpaRepositoryAdapter;
    }

    @Override
    public TaskModel save(TaskModel taskModel) {
        return toModel(taskJpaRepositoryAdapter.save(toEntity(taskModel)));
    }

    @Override
    public TaskModel getTaskById(Integer id) {
        return toModel(taskJpaRepositoryAdapter.findById(id).get());
    }

    @Override
    public List<TaskModel> getTasksByName(String name) {
        List<TaskEntity> taskEntities = taskJpaRepositoryAdapter.findByName(name);
        return toModelList(taskEntities);
    }

    @Override
    public TaskModel transitionTask(Integer id) {
        TaskModel taskModel = getTaskById(id);
        return toModel(taskJpaRepositoryAdapter.save(toEntity(taskModel.transition())));
    }

    @Override
    public TaskModel inactiveTask(Integer id) {
        TaskModel taskModel = getTaskById(id);
        return toModel(taskJpaRepositoryAdapter.save(toEntity(taskModel.inactive())));
    }

    @Override
    public TaskModel activeTask(Integer id) {
        TaskModel taskModel = getTaskById(id);
        return toModel(taskJpaRepositoryAdapter.save(toEntity(taskModel.active())));
    }

    @Override
    public List<TaskModel> getAllTasks() {
        List<TaskEntity> taskEntities = taskJpaRepositoryAdapter.findAll();
        return toModelList(taskEntities);
    }

    @Override
    public List<TaskModel> getAllTaskByBoardId(Integer boardId) {
        List<TaskEntity> tasksEntities = taskJpaRepositoryAdapter.findByBoardIdAndParentIdIsNull(boardId);
        return toModelList(tasksEntities);
    }

    @Override
    public List<TaskModel> getAllTaskByParentId(Integer parentId) {
        List<TaskEntity> tasksEntities = taskJpaRepositoryAdapter.findByParentIdAndBoardIdIsNull(parentId);
        return toModelList(tasksEntities);
    }
}
