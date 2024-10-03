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

class InactiveBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private InactiveBoardService inactiveBoardService;
    private BoardModel mockBoardModel;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);
        inactiveBoardService = new InactiveBoardService(boardRepositoryMock);

        mockBoardModel = MockUtils.makeBoardModelMock();
    }

    @Test
    void shouldInactivateBoardWhenBoardIsActive() {
        // Arrange
        Mockito.when(boardRepositoryMock.inactive(mockBoardModel.getId())).thenReturn(mockBoardModel.inactive());

        // Act
        BoardModel response = inactiveBoardService.execute(mockBoardModel.getId());

        // Assert
        assertNotNull(response);
        assertEquals(mockBoardModel.getId(), response.getId());
        assertEquals(mockBoardModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @Test
    void shouldThrowExceptionWhenBoardIsAlreadyInactive() {
        // Arrange
        mockBoardModel = mockBoardModel.inactive();
        Mockito.when(boardRepositoryMock.inactive(mockBoardModel.getId())).thenReturn(mockBoardModel);

        // Act & Assert
        WarningException exception = assertThrows(WarningException.class, () -> mockBoardModel.inactive());
        assertEquals("It cannot be inactivated, already in this state", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBoardIsDone() {
        // Arrange
        BoardModel doneBoardModel = BoardModel.fromData(mockBoardModel.getId(),
                mockBoardModel.getTitle(),
                mockBoardModel.getDescription(),
                 "003");
        Mockito.when(boardRepositoryMock.inactive(doneBoardModel.getId())).thenReturn(doneBoardModel);

        // Act & Assert
        WarningException exception = assertThrows(WarningException.class, doneBoardModel::inactive);
        assertEquals("It cannot be inactivated, it was already done", exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
