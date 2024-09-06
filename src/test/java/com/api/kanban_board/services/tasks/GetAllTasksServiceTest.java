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

class GetAllTasksServiceTest {

    TaskRepository taskRepositoryMock;
    GetAllTasksService getAllTasksService;
    List<TaskModel> mockTaskList;

    @BeforeEach
    void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);

        getAllTasksService = new GetAllTasksService(taskRepositoryMock);

        MockUtils mockUtils = new MockUtils();
        mockTaskList = List.of(mockUtils.makeTaskModelMock());
    }

    @Test
    void shouldReturnAllTasksWhenServiceIsExecuted() {
        // Arrange
        Mockito.when(taskRepositoryMock.getAllTasks()).thenReturn(mockTaskList);

        // Act
        List<TaskModel> response = getAllTasksService.execute();

        // Assert
        assertEquals(1, response.size());
        assertEquals(mockTaskList.get(0).getId(), response.get(0).getId());
        assertEquals(mockTaskList.get(0).getName(), response.get(0).getName());
        assertEquals(mockTaskList.get(0).getDescription(), response.get(0).getDescription());
        assertEquals(mockTaskList.get(0).getStatus().getCode(), response.get(0).getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskRepositoryMock);
    }


}
