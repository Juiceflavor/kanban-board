package com.api.kanban_board.services.boards;

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

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        getAllBoardsService = new GetAllBoardsService(boardRepositoryMock);

        BoardModel mockBoard = BoardModel.fromData(1L, "Primer tablero",
                "Primer tablero", "001");

        List<BoardModel> mockBoardList = List.of(mockBoard);

        Mockito.when(boardRepositoryMock.getAllBoards()).thenReturn(mockBoardList);
    }

    @Test
    void getAllBoards() {
        List<BoardModel> result = getAllBoardsService.execute();

        assertNotNull(result);

        assertEquals(1, result.size());

        BoardModel board = result.get(0);
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