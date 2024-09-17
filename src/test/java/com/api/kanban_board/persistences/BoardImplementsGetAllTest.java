package com.api.kanban_board.persistences;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardImplementsGetAllTest {

    private BoardJpaRepositoryAdapter boardJpaRepositoryAdapterMock;
    private BoardImplements boardImplements;
    private List<BoardEntity> boardEntityListMock;
    private BoardModel boardModelMock;

    @BeforeEach
    void setUp() {
        boardJpaRepositoryAdapterMock = Mockito.mock(BoardJpaRepositoryAdapter.class);
        boardImplements = new BoardImplements(boardJpaRepositoryAdapterMock);

        
        boardEntityListMock = List.of(MockUtils.makeBoardEntityMock());
        boardModelMock = MockUtils.makeBoardModelMock();
    }

    @Test
    void shouldReturnAllBoardsWhenCalled() {
        // Arrange
        Mockito.when(boardJpaRepositoryAdapterMock.findAll()).thenReturn(boardEntityListMock);

        // Act
        List<BoardModel> response = boardImplements.getAllBoards();

        // Assert
        assertEquals(1, response.size());
        assertEquals(boardModelMock.getId(), response.get(0).getId());
        assertEquals(boardModelMock.getTitle(), response.get(0).getTitle());
        assertEquals(boardModelMock.getDescription(), response.get(0).getDescription());
        assertEquals(boardModelMock.getStatus().getCode(), response.get(0).getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardJpaRepositoryAdapterMock);
    }
}
