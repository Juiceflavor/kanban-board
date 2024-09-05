package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetAllBoardsServiceTest {

    private BoardRepository boardRepositoryMock;

    private GetAllBoardsService getAllBoardsService;

    private List<BoardModel> mockBoardList;

    private MockUtils mockUtils;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        getAllBoardsService = new GetAllBoardsService(boardRepositoryMock);

        mockUtils = new MockUtils();
        mockBoardList = List.of(mockUtils.makeBoardModelMock());
    }

    @Test
    void shouldGetAllBoardsSuccess() {
        // Arrange
        Mockito.when(boardRepositoryMock.getAllBoards()).thenReturn(mockBoardList);

        // Act
        List<BoardModel> result = getAllBoardsService.execute();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Primer tablero", result.get(0).getTitle());
        assertEquals("Primer tablero", result.get(0).getDescription());
        assertEquals("001", result.get(0).getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
