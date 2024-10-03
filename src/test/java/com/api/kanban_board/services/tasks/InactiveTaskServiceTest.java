package com.api.kanban_board.services.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InactiveTaskServiceTest {

    private TaskRepository taskRepositoryMock;
    private InactiveTaskService inactiveTaskService;
    private TaskModel mockTaskModel;

    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);
        inactiveTaskService = new InactiveTaskService(taskRepositoryMock);

        mockTaskModel = MockUtils.makeTaskModelMock();
    }

    @Test
    void shouldInactiveTaskWhenIdExists() {
        // Arrange
        Mockito.when(taskRepositoryMock.inactiveTask(mockTaskModel.getId())).thenReturn(mockTaskModel.inactive());

        // Act
        TaskModel response = inactiveTaskService.execute(mockTaskModel.getId());

        // Assert
        assertNotNull(response);
        assertEquals(mockTaskModel.getId(), response.getId());
        assertEquals(mockTaskModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskRepositoryMock);
    }
}
