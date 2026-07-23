package com.ticketera.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Event")
public class EventTest {
    @Test
    @DisplayName("Should initialize event with correct values")
    public void shouldInitializeEventWithCorrectValues() {
        // Arrange
        Event event = new Event("Jazz Night", "Jazz Club", 500, 100);

        // Act
        String name = event.getName();
        String venue = event.getVenue();
        int capacity = event.getCapacity();
        int ticketIsSold = event.getTicketSold();

        // Assert
        assertEquals("Jazz Night", name, "Event name must be Jazz Night");
        assertEquals("Jazz Club", venue, "Venue must be Jazz Club");
        assertEquals(500, capacity, "Capacity must be 500");
        assertEquals(100, ticketIsSold, "Tickets sold must be 100");
    }

    @Test
    @DisplayName("Should return true when tickets are available")
    public void shouldReturnTrueWhenTicketsAreAvailable() {
        // Arrange
        Event event = new Event("Jazz Night", "Jazz Club", 500, 100);

        // Act
        boolean available = event.hasAvailability();

        // Assert
        assertTrue(available, "Should have availability when ticketSold < capacity");
    }

    @Test
    @DisplayName("Should return false when event is sold out")
    public void shouldReturnFalseWhenEventIsSoldOut() {
        // Arrange
        Event event = new Event("Jazz Night", "Jazz Club", 500, 500);
        
        // Act
        boolean available = event.hasAvailability();

        // Assert
        assertFalse(available, "Should not have availability when ticketSold == capacity");
    }

    @Test
    @DisplayName("Should calculate available tickets correctly")
    public void shouldCalculateAvailableTicketsCorrectly() {
        // Arrange
        Event event = new Event("Jazz Night", "Jazz Club", 500, 100);

        // Act
        int available = event.getAvailableTickets();

        // Assert
        assertEquals(400, available, "Available tickets must be 400");
    }
}
