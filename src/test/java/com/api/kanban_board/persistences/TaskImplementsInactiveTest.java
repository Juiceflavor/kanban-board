package com.api.kanban_board.persistences;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.api.kanban_board.persistences.adapters.TaskJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.api.kanban_board.mappers.TaskMapper.toEntity;
import static org.junit.jupiter.api.Assertions.*;

class TaskImplementsInactiveTest {

    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapterMock;
    private TaskImplements taskImplements;
    private TaskModel taskModelMock;

    @BeforeEach
    void setUp() {
        taskJpaRepositoryAdapterMock = Mockito.mock(TaskJpaRepositoryAdapter.class);
        taskImplements = new TaskImplements(taskJpaRepositoryAdapterMock);

        taskModelMock = MockUtils.makeTaskModelMock();
    }

    @Test
    void shouldInactiveTaskWhenIdExist() {
        // Arrange
        Mockito.when(taskJpaRepositoryAdapterMock.findById(taskModelMock.getId()))
                .thenReturn(java.util.Optional.ofNullable(toEntity(taskModelMock)));
        Mockito.when(taskJpaRepositoryAdapterMock.save(Mockito.any())).thenReturn(toEntity(taskModelMock.inactive()));

        // Act
        TaskModel response = taskImplements.inactiveTask(taskModelMock.getId());

        // Assert
        assertNotNull(response);
        assertEquals(taskModelMock.getId(), response.getId());
        assertEquals(taskModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskJpaRepositoryAdapterMock);
    }
}
