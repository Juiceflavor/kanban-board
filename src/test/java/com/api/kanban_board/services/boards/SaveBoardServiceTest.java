package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SaveBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private SaveBoardService saveBoardService;
    private BoardModel mockBoardModel;
    private BoardModel response;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        saveBoardService = new SaveBoardService(boardRepositoryMock);

        MockUtils mockUtils = new MockUtils();
        mockBoardModel = mockUtils.makeBoardModelMock();
    }

    @Test
    void shouldSaveBoardSuccess() {
        // Arrange
        Mockito.when(boardRepositoryMock.save(Mockito.any())).thenReturn(mockBoardModel);

        // Act
        response = saveBoardService.execute(mockBoardModel);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Primer tablero", response.getTitle());
        assertEquals("Primer tablero", response.getDescription());
        assertEquals("001", response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
    }
}
