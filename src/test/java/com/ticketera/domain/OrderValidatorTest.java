package com.ticketera.domain;

import com.ticketera.domain.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Order Validator")
public class OrderValidatorTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    @DisplayName("Should throw InvalidOrderException when quantity is less than or equal to zero")
    public void shouldThrowInvalidOrderExceptionWhenQuantityIsInvalid(int invalidQuantity) {
        // Arrange
        OrderValidator validator = new OrderValidator();

        // Act & Assert
        assertThrows(InvalidOrderException.class, () -> {
            validator.validate(invalidQuantity, 50.0);
        });
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -100.0})
    @DisplayName("Should throw InvalidOrderException when price is less than or equal to zero")
    public void shouldThrowInvalidOrderExceptionWhenPriceIsInvalid(double invalidPrice) {
        // Arrange
        OrderValidator validator = new OrderValidator();

        // Act & Assert
        assertThrows(InvalidOrderException.class, () -> {
            validator.validate(2, invalidPrice);
        });
    }

    @Test
    @DisplayName("Should validate successfully when quantity and price are valid")
    public void shouldValidateSuccessfullyWhenQuantityAndPriceAreValid() {
        // Arrange
        OrderValidator validator = new OrderValidator();

        // Act & Assert
        assertDoesNotThrow(() -> {
            validator.validate(2, 50.0);
        });
    }
}
