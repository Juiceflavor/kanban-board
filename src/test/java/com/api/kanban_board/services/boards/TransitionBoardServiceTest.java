package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.exceptions.WarningException;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TransitionBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private TransitionBoardService transitionBoardService;
    private BoardModel mockBoardModel;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        transitionBoardService = new TransitionBoardService(boardRepositoryMock);

        mockBoardModel = MockUtils.makeBoardModelMock();
    }

    @Test
    void shouldTransitionWhenBoardExists() {
        // Arrange
        Mockito.when(boardRepositoryMock.transitionBoard(mockBoardModel.getId())).
                thenReturn(mockBoardModel.transition());

        // Act
        BoardModel response = transitionBoardService.execute(mockBoardModel.getId());

        // Assert
        assertNotNull(response);
        assertEquals(mockBoardModel.getId(), response.getId());
        assertEquals(mockBoardModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @Test
    void shouldTransitionFromInProgressToDone() {
        // Arrange
        mockBoardModel = mockBoardModel.transition();
        Mockito.when(boardRepositoryMock.transitionBoard(mockBoardModel.getId())).
                thenReturn(mockBoardModel.transition());

        // Act
        BoardModel response = transitionBoardService.execute(mockBoardModel.getId());

        // Assert
        assertNotNull(response);
        assertEquals(mockBoardModel.getId(), response.getId());
        assertEquals(mockBoardModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @Test
    void shouldThrowExceptionWhenBoardIsDone() {
        // Act & Assert
        mockBoardModel = mockBoardModel.transition().transition();
        WarningException exception = assertThrows(WarningException.class, () -> mockBoardModel.transition());
        assertEquals("The Done has finished", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBoardIsInactive() {
        // Arrange
        mockBoardModel = mockBoardModel.inactive();

        // Act & Assert
        WarningException exception = assertThrows(WarningException.class, () -> mockBoardModel.transition());
        assertEquals("Cannot transition from Inactive", exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
