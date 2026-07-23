package com.ticketera.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Order Service")
public class OrderServiceTest {
    @Test
    @DisplayName("Should fail when processing order with null eventId")
    public void shouldFailWhenProcessingOrderWithNullEventId() {
        // Arrange
        EventRepository repositoryMock = mock(EventRepository.class);
        NotificationService notifierMock = mock(NotificationService.class);
        OrderService orderService = new OrderService(repositoryMock, notifierMock);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(null, 2);
        });
    }

    @Test
    @DisplayName("Should fail when processing order with empty eventId")
    public void shouldFailWhenProcessingOrderWithEmptyEventId() {
        // Arrange
        EventRepository repositoryMock = mock(EventRepository.class);
        NotificationService notifierMock = mock(NotificationService.class);
        OrderService orderService = new OrderService(repositoryMock, notifierMock);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder("", 2);
        });
    }
    @Test
    @DisplayName("Should fail when processing order with event not found")
    public void shouldFailWhenProcessingOrderWithEventNotFound() {
        // Arrange
        EventRepository repositoryMock = mock(EventRepository.class);
        NotificationService notifierMock = mock(NotificationService.class);
        OrderService orderService = new OrderService(repositoryMock, notifierMock);
        when(repositoryMock.findById("EVT-999")).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder("EVT-999", 2);
        });
    }

    @Test
    @DisplayName("Should process order successfully and send notification with event name")
    public void shouldProcessOrderSuccessfullyAndSendNotification() {
        // Arrange
        EventRepository repositoryMock = mock(EventRepository.class);
        NotificationService notifierMock = mock(NotificationService.class);
        OrderService orderService = new OrderService(repositoryMock, notifierMock);
        when(repositoryMock.findById("EVT-001")).thenReturn(new Event("Jazz Night", "Stadium A", 500, 100));

        // Act
        orderService.processOrder("EVT-001", 2);

        // Assert
        verify(notifierMock, times(1)).send("admin@ticketera.com",
            "Order processed for: Jazz Night (2 tickets), with ID: EVT-001");
    }
}
