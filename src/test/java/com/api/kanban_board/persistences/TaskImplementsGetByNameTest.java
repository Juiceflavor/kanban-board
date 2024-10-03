package com.api.kanban_board.persistences;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskImplementsGetByNameTest {

    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapterMock;
    private TaskImplements taskImplements;
    private TaskModel taskModelMock;
    private TaskEntity taskEntityMock;

    @BeforeEach
    void setUp() {
        taskJpaRepositoryAdapterMock = Mockito.mock(TaskJpaRepositoryAdapter.class);
        taskImplements = new TaskImplements(taskJpaRepositoryAdapterMock);

        taskModelMock = MockUtils.makeTaskModelMock();
        taskEntityMock = MockUtils.makeTaskEntityMock();
    }

    @Test
    void shouldFindTasksWhenNameExists() {
        // Arrange
        Mockito.when(taskJpaRepositoryAdapterMock.findByName(taskModelMock.getName()))
                .thenReturn(List.of(taskEntityMock));

        // Act
        List<TaskModel> response = taskImplements.getTasksByName(taskModelMock.getName());

        // Assert
        assertNotNull(response);
        assertEquals(taskModelMock.getId(), response.get(0).getId());
        assertEquals(taskModelMock.getName(), response.get(0).getName());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskJpaRepositoryAdapterMock);
    }
}
