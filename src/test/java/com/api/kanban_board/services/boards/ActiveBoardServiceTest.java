package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ActiveBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private ActiveBoardService activeBoardService;
    private BoardModel boardModelMock;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);
        activeBoardService = new ActiveBoardService(boardRepositoryMock);

        boardModelMock = MockUtils.makeBoardModelMock().inactive();
    }

    @Test
    void shouldActiveBoardWhenInactive() {
        // Arrange
        Mockito.when(boardRepositoryMock.active(boardModelMock.getId())).thenReturn(boardModelMock.active());

        // Act
        BoardModel response = activeBoardService.execute(boardModelMock.getId());

        // Assert
        assertEquals(boardModelMock.getId(), response.getId());
        assertEquals(boardModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
