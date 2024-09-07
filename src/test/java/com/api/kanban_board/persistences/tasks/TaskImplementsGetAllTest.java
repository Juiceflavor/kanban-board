package com.api.kanban_board.persistences.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.TaskImplements;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskImplementsGetAllTest {
    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapterMock;
    private TaskImplements taskImplements;
    private List<TaskEntity> taskEntityListMock;
    private TaskModel taskModelMock;

    @BeforeEach
    void setUp() {
        taskJpaRepositoryAdapterMock = Mockito.mock(TaskJpaRepositoryAdapter.class);
        taskImplements = new TaskImplements(taskJpaRepositoryAdapterMock);

        MockUtils mockUtils = new MockUtils();
        taskEntityListMock = List.of(mockUtils.makeTaskEntityMock());
        taskModelMock = mockUtils.makeTaskModelMock();
    }

    @Test
    void shouldReturnAllTasksWhenTasksExist() {
        // Arrange
        Mockito.when(taskJpaRepositoryAdapterMock.findAll()).thenReturn(taskEntityListMock);

        // Act
        List<TaskModel> response = taskImplements.getAllTasks();

        // Assert
        assertEquals(1, response.size());
        assertEquals(taskModelMock.getId(), response.get(0).getId());
        assertEquals(taskModelMock.getName(), response.get(0).getName());
        assertEquals(taskModelMock.getDescription(), response.get(0).getDescription());
        assertEquals(taskModelMock.getParent_id(), response.get(0).getParent_id());
        assertEquals(taskModelMock.getBoard_id(), response.get(0).getBoard_id());
        assertEquals(taskModelMock.getStatus().getCode(), response.get(0).getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskJpaRepositoryAdapterMock);
    }
}
