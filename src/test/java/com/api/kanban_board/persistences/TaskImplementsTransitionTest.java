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

import static com.api.kanban_board.mappers.TaskMapper.toEntity;
import static org.junit.jupiter.api.Assertions.*;

class TaskImplementsTransitionTest {

    private TaskJpaRepositoryAdapter taskJpaRepositoryAdapterMock;
    private TaskImplements taskImplements;
    private TaskModel taskModelMock;
    private TaskModel transitionedTaskModelMock;
    private TaskEntity taskEntityMock;

    @BeforeEach
    void setUp() {
        taskJpaRepositoryAdapterMock = Mockito.mock(TaskJpaRepositoryAdapter.class);
        taskImplements = new TaskImplements(taskJpaRepositoryAdapterMock);

        taskModelMock = MockUtils.makeTaskModelMock();
        transitionedTaskModelMock = taskModelMock.transition();
        taskEntityMock = MockUtils.makeTaskEntityMock();
    }

    @Test
    void transitionTask() {
        // Arrange
        Mockito.when(taskJpaRepositoryAdapterMock.findById(taskModelMock.getId()))
                .thenReturn(Optional.ofNullable(taskEntityMock));
        Mockito.when(taskJpaRepositoryAdapterMock.save(Mockito.any())).thenReturn(toEntity(transitionedTaskModelMock));

        // Act
        TaskModel response = taskImplements.transitionTask(taskModelMock.getId());

        // Assert
        assertNotNull(response);
        assertEquals(transitionedTaskModelMock.getId(), response.getId());
        assertEquals(transitionedTaskModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskJpaRepositoryAdapterMock);
    }
}
