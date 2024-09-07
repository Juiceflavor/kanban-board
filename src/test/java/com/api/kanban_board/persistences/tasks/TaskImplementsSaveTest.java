package com.api.kanban_board.persistences.tasks;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.TaskImplements;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TaskImplementsSaveTest {

    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapterMock;
    private TaskImplements taskImplements;
    private TaskModel taskModelMock;
    private TaskEntity taskEntityMock;

    @BeforeEach
    void setUp() {
        taskJpaRepositoryAdapterMock = Mockito.mock(TaskJpaRepositoryAdapter.class);
        taskImplements = new TaskImplements(taskJpaRepositoryAdapterMock);

        MockUtils mockUtils = new MockUtils();
        taskModelMock = mockUtils.makeTaskModelMock();
        taskEntityMock = mockUtils.makeTaskEntityMock();
    }

    @Test
    void shouldSaveTaskWhenValidDataIsProvided() {
        // Arrange
        Mockito.when(taskJpaRepositoryAdapterMock.save(Mockito.any())).thenReturn(taskEntityMock);

        // Act
        TaskModel response = taskImplements.save(taskModelMock);

        // Assert
        assertEquals(response.getId(), taskModelMock.getId());
        assertEquals(response.getName(), taskModelMock.getName());
        assertEquals(response.getDescription(), taskModelMock.getDescription());
        assertEquals(response.getStatus().getCode(), taskModelMock.getStatus().getCode());
        assertEquals(response.getParent_id(), taskModelMock.getParent_id());
        assertEquals(response.getBoard_id(), taskModelMock.getBoard_id());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskJpaRepositoryAdapterMock);
    }
}
