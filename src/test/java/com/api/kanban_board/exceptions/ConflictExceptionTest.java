package com.api.kanban_board.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConflictExceptionTest {

    @Test
    void shouldThrowConflictExceptionWithCorrectMessage() {
        // Arrange
        String expectedMessage = "Resource conflict occurred";

        // Act
        ConflictException exception = new ConflictException(expectedMessage);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}
