package com.api.kanban_board.persistences.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.BoardImplements;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardImplementsGetByIdTest {

    private BoardJpaRepositoryAdapter boardJpaRepositoryAdapterMock;
    private BoardImplements boardImplements;
    private BoardModel boardModelMock;
    private BoardEntity boardEntityMock;
    private Long id;

    @BeforeEach
    void setUp() {
        boardJpaRepositoryAdapterMock = Mockito.mock(BoardJpaRepositoryAdapter.class);
        boardImplements = new BoardImplements(boardJpaRepositoryAdapterMock);

        MockUtils mockUtils = new MockUtils();
        boardModelMock = mockUtils.makeBoardModelMock();
        boardEntityMock = mockUtils.makeBoardEntityMock();
        id = boardModelMock.getId();
    }

    @Test
    void shouldReturnBoardWhenIdExists() {
        // Arrange
        Mockito.when(boardJpaRepositoryAdapterMock.findById(id)).thenReturn(Optional.ofNullable(boardEntityMock));

        // Act
        BoardModel response = boardImplements.getBoardById(id);

        // Assert
        assertNotNull(response);
        assertEquals(boardModelMock.getId(), response.getId());
        assertEquals(boardModelMock.getTitle(), response.getTitle());
        assertEquals(boardModelMock.getDescription(), response.getDescription());
        assertEquals(boardModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardJpaRepositoryAdapterMock);
    }
}
