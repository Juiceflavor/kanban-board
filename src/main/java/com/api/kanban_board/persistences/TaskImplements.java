    package com.api.kanban_board.persistences;

    import com.api.kanban_board.entities.TaskEntity;
    import com.api.kanban_board.exceptions.CustomException;
    import com.api.kanban_board.exceptions.ExceptionDetails;
    import com.api.kanban_board.models.TaskModel;
    import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
    import com.api.kanban_board.repositories.TaskRepository;
    import org.springframework.dao.DataAccessException;
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
            try {
                TaskEntity taskEntity = taskJpaRepositoryAdapter.save(toEntity(taskModel));
                return toModel(taskEntity);
            } catch (DataAccessException e) {
                throw new CustomException("Error saving task in DB",
                        new ExceptionDetails("Database error", "Error"), e);
            }
        }

        @Override
        public TaskModel getTaskById(Long id) {
            try {
                TaskEntity taskEntity = taskJpaRepositoryAdapter.findById(id).orElseThrow(() ->
                        new CustomException("The task doesn't exist in DB",
                                new ExceptionDetails("Task not found", "Error")));
                return toModel(taskEntity);
            } catch (DataAccessException e) {
                throw new CustomException("Error retrieving task from DB",
                        new ExceptionDetails("Database error", "Error"), e);
            }
        }

        @Override
        public List<TaskModel> getAllTasks() {
            try {
                List<TaskEntity> taskEntities = taskJpaRepositoryAdapter.findAll();
                if (taskEntities.isEmpty()) {
                    throw new CustomException("No tasks found in DB",
                            new ExceptionDetails("No tasks available", "Error"));
                }
                return toModelList(taskEntities);
            } catch (DataAccessException e) {
                throw new CustomException("Error retrieving tasks from DB",
                        new ExceptionDetails("Couldn't retrieve tasks", "Error"), e);
            }
        }

        @Override
        public List<TaskModel> getAllTaskByBoardId(Long board_id) {
            try {
                List<TaskEntity> taskEntities = taskJpaRepositoryAdapter.getTasksByBoardId(board_id);
                if (taskEntities.isEmpty()) {
                    throw new CustomException("No tasks found for the given board ID",
                            new ExceptionDetails("No tasks available", "Error"));
                }

                return toModelList(taskEntities);
            } catch (DataAccessException e) {
                throw new CustomException("Error retrieving tasks from DB",
                        new ExceptionDetails("Couldn't retrieve tasks", "Error"), e);
            }
        }
    }
