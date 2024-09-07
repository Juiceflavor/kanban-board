package com.api.kanban_board.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarningExceptionTest {

    @Test
    void shouldThrowWarningExceptionWithCorrectMessage() {
        // Arrange
        String expectedMessage = "Resource conflict occurred";

        // Act
        WarningException exception = new WarningException(expectedMessage);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
