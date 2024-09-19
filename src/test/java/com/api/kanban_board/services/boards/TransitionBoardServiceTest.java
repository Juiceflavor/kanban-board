package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

class TransitionBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private TransitionBoardService transitionBoardService;
    private BoardModel mockBoardModel;
    private BoardModel mockTransitionedBoardModel;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        transitionBoardService = new TransitionBoardService(boardRepositoryMock);

        mockBoardModel = MockUtils.makeBoardModelMock();
        mockTransitionedBoardModel = mockBoardModel.transition();
    }

    @Test
    void shouldTransitionWhenBoardExists() {
        // Arrange
        Mockito.when(boardRepositoryMock.transitionBoard(mockBoardModel.getId())).
                thenReturn(mockTransitionedBoardModel);

        // Act
        BoardModel response = transitionBoardService.execute(mockBoardModel.getId());

        // Assert
        assertNotNull(response);
        assertEquals(mockTransitionedBoardModel.getId(), response.getId());
        assertEquals(mockTransitionedBoardModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
