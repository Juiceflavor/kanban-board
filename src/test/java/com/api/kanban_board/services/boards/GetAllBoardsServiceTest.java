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

class GetAllBoardsServiceTest {

    private BoardRepository boardRepositoryMock;
    private GetAllBoardsService getAllBoardsService;
    private List<BoardModel> mockBoardList;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        getAllBoardsService = new GetAllBoardsService(boardRepositoryMock);

        MockUtils mockUtils = new MockUtils();
        mockBoardList = List.of(mockUtils.makeBoardModelMock());
    }

    @Test
    void shouldGetAllBoardsSuccess() {
        // Arrange
        Mockito.when(boardRepositoryMock.getAllBoards()).thenReturn(mockBoardList);

        // Act
        List<BoardModel> response = getAllBoardsService.execute();

        // Assert
        assertEquals(1, response.size());
        assertEquals(mockBoardList.get(0).getId(), response.get(0).getId());
        assertEquals(mockBoardList.get(0).getTitle(), response.get(0).getTitle());
        assertEquals(mockBoardList.get(0).getDescription(), response.get(0).getDescription());
        assertEquals(mockBoardList.get(0).getStatus().getCode(), response.get(0).getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
