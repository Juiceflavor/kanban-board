package com.api.kanban_board.services.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TransitionTaskServiceTest {

    private TaskRepository taskRepositoryMock;
    private TransitionTaskService transitionTaskService;
    private TaskModel taskModelMock;
    private TaskModel transitionedTaskModelMock;

    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);
        transitionTaskService = new TransitionTaskService(taskRepositoryMock);

        taskModelMock = MockUtils.makeTaskModelMock();
        transitionedTaskModelMock = taskModelMock.transition();
    }

    @Test
    void shouldTransitionWhenIdExist() {
        // Arrange
        Mockito.when(taskRepositoryMock.transitionTask(taskModelMock.getId())).thenReturn(transitionedTaskModelMock);

        // Act
        TaskModel response = transitionTaskService.execute(taskModelMock.getId());

        // Assert
        assertNotNull(response);
        assertEquals(transitionedTaskModelMock.getId(), response.getId());
        assertEquals(transitionedTaskModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskRepositoryMock);
    }
}
