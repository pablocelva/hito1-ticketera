package com.ticketera.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Booking Confirmation")
public class BookingConfirmationTest {
    @Test
    @DisplayName("Should fail when sending confirmation with null email")
    public void shouldFailWhenSendingConfirmationWithNullEmail() {
        // Arrange
        NotificationService notifierMock = mock(NotificationService.class);
        BookingConfirmation confirmation = new BookingConfirmation(notifierMock);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            confirmation.sendConfirmation(null, "Jazz Night");
        });
    }

    @Test
    @DisplayName("Should fail when sending confirmation with empty email")
    public void shouldFailWhenSendingConfirmationWithEmptyEmail() {
        // Arrange
        NotificationService notifierMock = mock(NotificationService.class);
        BookingConfirmation confirmation = new BookingConfirmation(notifierMock);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            confirmation.sendConfirmation("", "Jazz Night");
        });
    }

    @Test
    @DisplayName("Should send confirmation successfully")
    public void shouldSendConfirmationSuccessfully() {
        // Arrange
        NotificationService notifierMock = mock(NotificationService.class);
        BookingConfirmation confirmation = new BookingConfirmation(notifierMock);

        // Act
        confirmation.sendConfirmation("customer@email.com", "Jazz Night");

        // Assert
        verify(notifierMock, times(1)).send("customer@email.com", "Booking confirmed for: Jazz Night");
    }
}
