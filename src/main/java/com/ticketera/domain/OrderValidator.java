package com.ticketera.domain;

import com.ticketera.domain.exception.InvalidOrderException;

public class OrderValidator {
    public void validate(int quantity, double price) {
        if (quantity <= 0) {
            throw new InvalidOrderException("Invalid quantity");
        }
        if (price <= 0) {
            throw new InvalidOrderException("Invalid price");
        }
    }
}
