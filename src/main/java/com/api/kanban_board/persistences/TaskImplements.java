package com.api.kanban_board.persistences;

import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.api.kanban_board.mappers.TaskMapper.*;

@Component
public class TaskImplements implements TaskRepository {

    @Autowired
    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapter;

    @Override
    public TaskModel save(TaskModel taskModel) {
        return toModelList(taskJpaRepositoryAdapter.save(toEntity(taskModel)));
    }

    @Override
    public TaskModel getTaskById(Long id) {
        return toModelList(taskJpaRepositoryAdapter.findById(id).get());
    }

    @Override
    public List<TaskModel> getAllTasks() {
        List<TaskEntity> taskEntities = taskJpaRepositoryAdapter.findAll();
        return toModelList(taskEntities);
    }
}
