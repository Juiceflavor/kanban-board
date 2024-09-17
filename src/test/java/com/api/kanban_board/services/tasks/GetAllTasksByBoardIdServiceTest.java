package com.api.kanban_board.services.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllTasksByBoardIdServiceTest {

    private TaskRepository taskRepositoryMock;
    private GetAllTasksByBoardIdService getAllTasksByBoardIdService;
    private List<TaskModel> mockTaskList;
    private Integer boardId;


    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);
        getAllTasksByBoardIdService = new GetAllTasksByBoardIdService(taskRepositoryMock);

        mockTaskList = List.of(MockUtils.makeTaskModelMock());
        boardId = mockTaskList.get(0).getBoardId();
    }

    @Test
    void shouldReturnAllTasksWhenValidBoardIdIsProvided() {
        // Arrange
        Mockito.when(taskRepositoryMock.getAllTaskByBoardId(boardId)).thenReturn(mockTaskList);

        // Act
        List<TaskModel> response = getAllTasksByBoardIdService.execute(boardId);

        // Assert
        assertEquals(1, response.size());
        assertEquals(mockTaskList.get(0).getId(), response.get(0).getId());
        assertEquals(mockTaskList.get(0).getName(), response.get(0).getName());
        assertEquals(mockTaskList.get(0).getDescription(), response.get(0).getDescription());
        assertEquals(mockTaskList.get(0).getStatus().getCode(), response.get(0).getStatus().getCode());
        assertEquals(mockTaskList.get(0).getParentId(), response.get(0).getParentId());
        assertEquals(mockTaskList.get(0).getBoardId(), response.get(0).getBoardId());
    }

    @AfterEach
    void tearDown() {
    }
}
