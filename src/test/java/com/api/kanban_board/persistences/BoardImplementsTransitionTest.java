package com.api.kanban_board.persistences;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.api.kanban_board.mappers.BoardMapper.toEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class BoardImplementsTransitionTest {

    private BoardJpaRepositoryAdapter boardJpaRepositoryAdapterMock;
    private BoardImplements boardImplements;
    private BoardModel boardModelMock;
    private BoardModel transitionedBoardModelMock;
    private BoardEntity boardEntityMock;

    @BeforeEach
    void setUp() {
        boardJpaRepositoryAdapterMock = mock(BoardJpaRepositoryAdapter.class);
        boardImplements = new BoardImplements(boardJpaRepositoryAdapterMock);

        boardModelMock = MockUtils.makeBoardModelMock();
        transitionedBoardModelMock = boardModelMock.transition();
        boardEntityMock = MockUtils.makeBoardEntityMock();
    }

    @Test
    void shouldTransitionBoardWhenIdExists() {
        // Arrange
        Mockito.when(boardJpaRepositoryAdapterMock.findById(boardModelMock.getId()))
                .thenReturn(Optional.ofNullable(boardEntityMock));
        Mockito.when(boardJpaRepositoryAdapterMock.save(Mockito.any())).thenReturn(toEntity(transitionedBoardModelMock));

        // Act
        BoardModel response = boardImplements.transitionBoard(boardModelMock.getId());

        // Assert
        assertNotNull(response);
        assertEquals(transitionedBoardModelMock.getId(), response.getId());
        assertEquals(transitionedBoardModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() throws Exception {
        Mockito.reset(boardJpaRepositoryAdapterMock);
    }
}