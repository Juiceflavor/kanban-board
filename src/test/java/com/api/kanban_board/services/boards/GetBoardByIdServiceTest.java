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

class GetBoardByIdServiceTest {

    private BoardRepository boardRepositoryMock;
    private GetBoardByIdService getBoardByIdService;
    private BoardModel mockBoardModel;
    private Integer id;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);

        getBoardByIdService = new GetBoardByIdService(boardRepositoryMock);

        mockBoardModel = MockUtils.makeBoardModelMock();

        id = mockBoardModel.getId();
    }

    @Test
    void shouldGetAllBoardsByIdSuccess() {
        // Arrange
        Mockito.when(boardRepositoryMock.getBoardById(id)).thenReturn(mockBoardModel);

        // Act
        BoardModel response = getBoardByIdService.execute(id);

        // Assert
        assertNotNull(response);
        assertEquals(mockBoardModel.getId(), response.getId());
        assertEquals(mockBoardModel.getTitle(), response.getTitle());
        assertEquals(mockBoardModel.getDescription(), response.getDescription());
        assertEquals(mockBoardModel.getStatus().getCode(), response.getStatus().getCode());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }

}
