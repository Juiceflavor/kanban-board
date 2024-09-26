package com.api.kanban_board.services.boards;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.exceptions.WarningException;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.repositories.BoardRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActiveBoardServiceTest {

    private BoardRepository boardRepositoryMock;
    private ActiveBoardService activeBoardService;
    private BoardModel boardModelMock;

    @BeforeEach
    void setUp() {
        boardRepositoryMock = Mockito.mock(BoardRepository.class);
        activeBoardService = new ActiveBoardService(boardRepositoryMock);

        boardModelMock = MockUtils.makeBoardModelMock().inactive();
    }

    @Test
    @Order(1)
    void shouldActiveBoardWhenInactive() {
        // Arrange
        Mockito.when(boardRepositoryMock.active(boardModelMock.getId())).thenReturn(boardModelMock.active());

        // Act
        BoardModel response = activeBoardService.execute(boardModelMock.getId());

        // Assert
        assertEquals(boardModelMock.getId(), response.getId());
        assertEquals(boardModelMock.getStatus().getCode(), response.getStatus().getCode());
    }

    @Test
    @Order(2)
    void ShouldThrowExceptionWhenAllReadyActive() {
        // Arrange
        boardModelMock = boardModelMock.active();
        Mockito.when(boardRepositoryMock.active(boardModelMock.getId())).thenReturn(boardModelMock);

        // Act & Assert
        WarningException exception = assertThrows(WarningException.class, () -> boardModelMock.active());
        assertEquals("It cannot be activated, isn't in the status Inactive", exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(boardRepositoryMock);
    }
}
