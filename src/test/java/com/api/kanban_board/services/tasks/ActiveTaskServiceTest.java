package com.api.kanban_board.services.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ActiveTaskServiceTest {

    private TaskRepository taskRepositoryMock;
    private ActiveTaskService activeTaskService;
    private TaskModel taskModelMock;

    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);
        activeTaskService = new ActiveTaskService(taskRepositoryMock);

        taskModelMock = MockUtils.makeTaskModelMock().inactive();
    }

    @Test
    void shouldActiveTasksWhenIdExists() {
        // Arrange
        Mockito.when(taskRepositoryMock.activeTask(taskModelMock.getId())).thenReturn(taskModelMock.active());

        // Act
        TaskModel response = activeTaskService.execute(taskModelMock.getId());

        // Assert
        assertNotNull(response);
        assertEquals(taskModelMock.getId(), response.getId());
        assertEquals(taskModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskRepositoryMock);
    }
}
