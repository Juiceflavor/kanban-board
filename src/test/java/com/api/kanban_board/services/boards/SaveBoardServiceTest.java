package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.exceptions.WarningException;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.models.CustomExcpetion;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private SaveBoardService saveBoardService;
    private BoardModel mockBoardModel;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        saveBoardService = new SaveBoardService(boardRepositoryMock);

        mockBoardModel = MockUtils.makeBoardModelMock();
    }

    @Test
    void shouldSaveBoardSuccess() {
        // Arrange
        Mockito.when(boardRepositoryMock.save(Mockito.any())).thenReturn(mockBoardModel);

        // Act
        BoardModel response = saveBoardService.execute(mockBoardModel);

        // Assert
        assertNotNull(response);
        assertEquals(mockBoardModel.getId(), response.getId());
        assertEquals(mockBoardModel.getTitle(), response.getTitle());
        assertEquals(mockBoardModel.getDescription(), response.getDescription());
        assertEquals(mockBoardModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @Test
    void shouldShowThrowExceptionWhenTheNameExists() {
        // Arrange
        Mockito.when(boardRepositoryMock.getBoardsByTitle(mockBoardModel.getTitle()))
                .thenReturn(List.of(mockBoardModel));

        // Act
        assertThrows(WarningException.class, () -> saveBoardService.execute(mockBoardModel));

        // Assert
        Mockito.verify(boardRepositoryMock, Mockito.never()).save(Mockito.any());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
