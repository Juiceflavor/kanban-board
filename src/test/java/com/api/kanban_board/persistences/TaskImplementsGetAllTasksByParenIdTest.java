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

class TaskImplementsGetAllTasksByParenIdTest {

    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapterMock;
    private TaskImplements taskImplements;
    private List<TaskEntity> taskEntityListMock;
    private TaskModel taskModelMock;
    private Integer id;

    @BeforeEach
    void setUp() {
        taskJpaRepositoryAdapterMock = Mockito.mock(TaskJpaRepositoryAdapter.class);
        taskImplements = new TaskImplements(taskJpaRepositoryAdapterMock);


        taskEntityListMock = List.of(MockUtils.makeTaskEntityMock());
        taskModelMock = MockUtils.makeTaskModelMock();
        id = taskModelMock.getId();
    }

    @Test
    void shouldReturnAllTasksForGivenParentId() {
        // Arrange
        Mockito.when(taskJpaRepositoryAdapterMock.findByParentIdAndBoardIdIsNull(id)).thenReturn(taskEntityListMock);

        // Act
        List<TaskModel> response = taskImplements.getAllTaskByParentId(id);

        // Assert
        assertEquals(1, response.size());
        assertEquals(taskModelMock.getId(), response.get(0).getId());
        assertEquals(taskModelMock.getName(), response.get(0).getName());
        assertEquals(taskModelMock.getDescription(), response.get(0).getDescription());
        assertEquals(taskModelMock.getParentId(), response.get(0).getParentId());
        assertEquals(taskModelMock.getBoardId(), response.get(0).getBoardId());
        assertEquals(taskModelMock.getStatus().getCode(), response.get(0).getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskJpaRepositoryAdapterMock);
    }
}
