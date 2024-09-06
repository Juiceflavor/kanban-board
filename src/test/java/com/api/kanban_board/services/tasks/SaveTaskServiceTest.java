package com.api.kanban_board.services.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SaveTaskServiceTest {

    private TaskRepository taskRepository;
    private SaveTaskService saveTaskService;
    private TaskModel mockTaskModel;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);

        saveTaskService = new SaveTaskService(taskRepository);

        MockUtils mockUtils = new MockUtils();
        mockTaskModel = mockUtils.makeTaskModelMock();
    }

    @Test
    void shouldSaveTaskWhenAllFieldsAreValid() {
        // Arrange
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(mockTaskModel);

        // Act
        TaskModel response = saveTaskService.execute(mockTaskModel);

        // Assert
        assertNotNull(response);
        assertEquals(mockTaskModel.getId(), response.getId());
        assertEquals(mockTaskModel.getName(), response.getName());
        assertEquals(mockTaskModel.getDescription(), response.getDescription());
        assertEquals(mockTaskModel.getStatus(), response.getStatus());
        assertEquals(mockTaskModel.getBoard_id(), response.getBoard_id());
        assertEquals(mockTaskModel.getParent_id(), response.getParent_id());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskRepository);
    }
}
