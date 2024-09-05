package com.api.kanban_board.services.boards;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.api.kanban_board.MockUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetAllBoardsServiceTest {

    private BoardRepository boardRepositoryMock;

    private GetAllBoardsService getAllBoardsService;

    private List<BoardModel> mockBoardList;

    private List<BoardModel> result;

    private BoardModel board;

    @BeforeEach
    void setUp() {
        // Arrange
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        getAllBoardsService = new GetAllBoardsService(boardRepositoryMock);

        mockBoardList = List.of(makeBoardModelMock());
    }

    @Test
    void shouldGetAllBoardsSuccess() {
        Mockito.when(boardRepositoryMock.getAllBoards()).thenReturn(mockBoardList);

        // Act
        result = getAllBoardsService.execute();
        board = result.get(0);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, board.getId());
        assertEquals("Primer tablero", board.getTitle());
        assertEquals("Primer tablero", board.getDescription());
        assertEquals("001", board.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
