package com.ticketera.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.ticketera.domain.exception.InvalidOrderException;
import com.ticketera.domain.exception.SoldOutException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Ticket Pool")
public class TicketPoolTest {
    @Test
    @DisplayName("Should throw InvalidOrderException when quantity is zero")
    public void shouldThrowInvalidOrderExceptionWhenQuantityIsZero() {
        // Arrange
        TicketPool pool = new TicketPool(10);

        // Act & Assert
        assertThrows(InvalidOrderException.class, () -> {
            pool.reserve(0);
        });
    }

    @Test
    @DisplayName("Should throw InvalidOrderException when quantity is negative")
    public void shouldThrowInvalidOrderExceptionWhenQuantityIsNegative() {
        // Arrange
        TicketPool pool = new TicketPool(10);

        // Act & Assert
        assertThrows(InvalidOrderException.class, () -> {
            pool.reserve(-5);
        });
    }

    @Test
    @DisplayName("Should throw SoldOutException when not enough tickets")
    public void shouldThrowSoldOutExceptionWhenNotEnoughTickets() {
        // Arrange
        TicketPool pool = new TicketPool(5);

        // Act & Assert
        assertThrows(SoldOutException.class, () -> {
            pool.reserve(10);
        });
    }

    @Test
    @DisplayName("Should reserve tickets successfully when available")
    public void shouldReserveTicketsSuccessfullyWhenAvailable() {
        // Arrange
        TicketPool pool = new TicketPool(10);

        // Act
        pool.reserve(3);

        // Assert
        assertEquals(7, pool.getAvailable(), "Available tickets must be 7 after reserving 3");
    }
}