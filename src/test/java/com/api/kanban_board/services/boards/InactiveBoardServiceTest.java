package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InactiveBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private InactiveBoardService inactiveBoardService;
    private BoardModel mockBoardModel;
    private BoardModel mockInactiveBoardModel;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);
        inactiveBoardService = new InactiveBoardService(boardRepositoryMock);

        mockBoardModel = MockUtils.makeBoardModelMock();
        mockInactiveBoardModel = mockBoardModel.inactive();
    }

    @Test
    void shouldInactivateBoardWhenBoardIsActive() {
        // Arrange
        when(boardRepositoryMock.inactive(mockBoardModel.getId())).thenReturn(mockInactiveBoardModel);

        // Act
        BoardModel response = inactiveBoardService.execute(mockBoardModel.getId());

        // Assert
        assertNotNull(response);
        assertEquals(mockInactiveBoardModel.getId(), response.getId());
        assertEquals(mockInactiveBoardModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
