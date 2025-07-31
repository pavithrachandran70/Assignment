package com.example.bookservice.exception;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseTest {

    @Test
    public void testGettersAndSetters() {
        LocalDateTime now = LocalDateTime.now();
        String message = "Test message";
        int status = 400;

        ErrorResponse errorResponse = new ErrorResponse(now, message, status);

        // Access and assert getters
        assertEquals(now, errorResponse.getTimestamp());
        assertEquals(message, errorResponse.getMessage());
        assertEquals(status, errorResponse.getStatus());

        // Set new values and test again
        LocalDateTime newTime = LocalDateTime.now().minusDays(1);
        errorResponse.setTimestamp(newTime);
        errorResponse.setMessage("Updated message");
        errorResponse.setStatus(500);

        assertEquals(newTime, errorResponse.getTimestamp());
        assertEquals("Updated message", errorResponse.getMessage());
        assertEquals(500, errorResponse.getStatus());
    }
}
