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
    @DisplayName("Should process order successfully and send notification")
    public void shouldProcessOrderSuccessfullyAndSendNotification() {
        // Arrange
        EventRepository repositoryMock = mock(EventRepository.class);
        NotificationService notifierMock = mock(NotificationService.class);
        OrderService orderService = new OrderService(repositoryMock, notifierMock);
        when(repositoryMock.findById("EVT-001")).thenReturn(new Event("Rock Night", "Stadium A", 500, 100));

        // Act
        orderService.processOrder("EVT-001", 2);

        // Assert
        verify(notifierMock, times(1)).send("admin@ticketera.com", "Order processed for event: EVT-001");
    }
}
