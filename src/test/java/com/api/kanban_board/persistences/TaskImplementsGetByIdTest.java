package com.api.kanban_board.persistences;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TaskImplementsGetByIdTest {

    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapterMock;
    private TaskImplements taskImplements;
    private TaskEntity taskEntityMock;
    private TaskModel taskModelMock;
    private Integer id;

    @BeforeEach
    void setUp() {
        taskJpaRepositoryAdapterMock = Mockito.mock(TaskJpaRepositoryAdapter.class);
        taskImplements = new TaskImplements(taskJpaRepositoryAdapterMock);

        
        taskEntityMock = MockUtils.makeTaskEntityMock();
        taskModelMock = MockUtils.makeTaskModelMock();
        id = taskModelMock.getId();
    }

    @Test
    void shouldReturnTaskWhenValidIdIsProvided() {
        // Arrange
        Mockito.when(taskJpaRepositoryAdapterMock.findById(id)).thenReturn(Optional.ofNullable(taskEntityMock));

        // Act
        TaskModel response = taskImplements.getTaskById(id);

        // Assert
        assertNotNull(response);
        assertEquals(taskModelMock.getId(), response.getId());
        assertEquals(taskModelMock.getName(), response.getName());
        assertEquals(taskModelMock.getDescription(), response.getDescription());
        assertEquals(taskModelMock.getParentId(), response.getParentId());
        assertEquals(taskModelMock.getBoardId(), response.getBoardId());
        assertEquals(taskModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskJpaRepositoryAdapterMock);
    }
}
