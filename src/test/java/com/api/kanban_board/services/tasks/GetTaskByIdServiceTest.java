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

class GetTaskByIdServiceTest {

    private TaskRepository taskRepositoryMock;
    private GetTaskByIdService getTaskByIdService;
    private TaskModel mockTaskModel;
    private Integer id;

    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);

        getTaskByIdService = new GetTaskByIdService(taskRepositoryMock);

        
        mockTaskModel = MockUtils.makeTaskModelMock();
        id = mockTaskModel.getId();
    }

    @Test
    void shouldReturnTaskWhenValidIdIsProvided() {
        // Arrange
        Mockito.when(taskRepositoryMock.getTaskById(id)).thenReturn(mockTaskModel);

        // Act
        TaskModel response = getTaskByIdService.execute(id);
        System.out.println(response);

        // Assert
        assertNotNull(response);
        assertEquals(mockTaskModel.getId(), response.getId());
        assertEquals(mockTaskModel.getName(), response.getName());
        assertEquals(mockTaskModel.getDescription(), response.getDescription());
        assertEquals(mockTaskModel.getStatus().getCode(), response.getStatus().getCode());
        assertEquals(mockTaskModel.getParentId(), response.getParentId());
        assertEquals(mockTaskModel.getBoardId(), response.getBoardId());
    }

    @AfterEach
    void tearDown() {
    }
}
