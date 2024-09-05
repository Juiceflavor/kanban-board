package com.api.kanban_board.services.boards;

import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.api.kanban_board.MockUtils.makeBoardModelMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetBoardByIdServiceTest {

    private BoardRepository boardRepositoryMock;
    private GetBoardByIdService getBoardByIdService;
    private BoardModel mockBoardModel;
    private Long id;
    private BoardModel response;

    @BeforeEach
    void setUp() {
        // Arrange
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        getBoardByIdService = new GetBoardByIdService(boardRepositoryMock);

        mockBoardModel = makeBoardModelMock();

        id = mockBoardModel.getId();
    }

    @Test
    void shouldGetAllBoardsByIdSuccess() {
        Mockito.when(boardRepositoryMock.getBoardById(id)).thenReturn(mockBoardModel);

        // Act
        response = getBoardByIdService.execute(id);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Primer tablero", response.getTitle());
        assertEquals("Primer tablero", response.getDescription());
        assertEquals("001", response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }

}
