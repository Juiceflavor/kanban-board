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
    private Long board_id;


    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);
        getAllTasksByBoardIdService = new GetAllTasksByBoardIdService(taskRepositoryMock);

        MockUtils mockUtils = new MockUtils();
        mockTaskList = List.of(mockUtils.makeTaskModelMock());
        board_id = mockTaskList.get(0).getBoard_id();
    }

    @Test
    void shouldReturnAllTasksWhenValidBoardIdIsProvided() {
        // Arrange
        Mockito.when(taskRepositoryMock.getAllTaskByBoardId(board_id)).thenReturn(mockTaskList);

        // Act
        List<TaskModel> response = getAllTasksByBoardIdService.execute(board_id);

        // Assert
        assertEquals(1, response.size());
        assertEquals(mockTaskList.get(0).getId(), response.get(0).getId());
        assertEquals(mockTaskList.get(0).getName(), response.get(0).getName());
        assertEquals(mockTaskList.get(0).getDescription(), response.get(0).getDescription());
        assertEquals(mockTaskList.get(0).getStatus().getCode(), response.get(0).getStatus().getCode());
        assertEquals(mockTaskList.get(0).getParent_id(), response.get(0).getParent_id());
        assertEquals(mockTaskList.get(0).getBoard_id(), response.get(0).getBoard_id());
    }

    @AfterEach
    void tearDown() {
    }
}
