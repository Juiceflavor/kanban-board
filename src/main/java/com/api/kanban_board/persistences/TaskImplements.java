package com.api.kanban_board.persistences;

import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import com.api.kanban_board.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.api.kanban_board.mappers.TaskMapper.*;

@Component
public class TaskImplements implements TaskRepository {

    @Autowired
    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapter;

    @Override
    public TaskModel save(TaskModel taskModel) {
        return toModel(taskJpaRepositoryAdapter.save(toEntity(taskModel)));
    }
}
