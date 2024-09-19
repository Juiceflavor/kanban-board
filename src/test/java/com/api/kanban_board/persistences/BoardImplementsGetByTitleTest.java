package com.api.kanban_board.persistences;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardImplementsGetByTitleTest {
    private BoardJpaRepositoryAdapter boardJpaRepositoryAdapterMock;
    private BoardImplements boardImplements;
    private BoardModel boardModelMock;
    private BoardEntity boardEntityMock;

    @BeforeEach
    void setUp() {
        boardJpaRepositoryAdapterMock = Mockito.mock(BoardJpaRepositoryAdapter.class);
        boardImplements = new BoardImplements(boardJpaRepositoryAdapterMock);

        boardModelMock = MockUtils.makeBoardModelMock();
        boardEntityMock = MockUtils.makeBoardEntityMock();
    }

    @Test
    void shouldFindBoardWhenTitleExits() {
        // Arrange
        Mockito.when(boardJpaRepositoryAdapterMock.findByTitle(boardModelMock.getTitle()))
                .thenReturn(List.of(boardEntityMock));

        // Act
        List<BoardModel> response = boardImplements.getBoardsByTitle(boardModelMock.getTitle());

        // Assert
        assertEquals(1, response.size());
        assertEquals(boardModelMock.getId(), response.get(0).getId());
        assertEquals(boardModelMock.getTitle(), response.get(0).getTitle());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardJpaRepositoryAdapterMock);
    }
}
