package com.api.kanban_board.services.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveTaskServiceTest {

    private TaskRepository taskRepositoryMock;
    private SaveTaskService saveTaskService;
    private TaskModel mockTaskModel;

    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);

        saveTaskService = new SaveTaskService(taskRepositoryMock);

        mockTaskModel = MockUtils.makeTaskModelMock();
    }

    @Test
    void shouldSaveTaskWhenAllFieldsAreValid() {
        // Arrange
        Mockito.when(taskRepositoryMock.save(Mockito.any())).thenReturn(mockTaskModel);

        // Act
        TaskModel response = saveTaskService.execute(mockTaskModel);

        // Assert
        assertNotNull(response);
        assertEquals(mockTaskModel.getId(), response.getId());
        assertEquals(mockTaskModel.getName(), response.getName());
        assertEquals(mockTaskModel.getDescription(), response.getDescription());
        assertEquals(mockTaskModel.getStatus(), response.getStatus());
        assertEquals(mockTaskModel.getBoardId(), response.getBoardId());
        assertEquals(mockTaskModel.getParentId(), response.getParentId());
    }

    @Test
    void shouldShowThrowExceptionWhenTheNameExists() {
        // Arrange
        Mockito.when(taskRepositoryMock.getTasksByName(mockTaskModel.getName()))
                .thenReturn(List.of(mockTaskModel));

        // Act
        assertThrows(Exception.class, () -> saveTaskService.execute(mockTaskModel));

        // Assert
        Mockito.verify(taskRepositoryMock, Mockito.never()).save(Mockito.any());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskRepositoryMock);
    }
}
