package com.ticketera.domain;

import com.ticketera.domain.exception.InvalidOrderException;
import com.ticketera.domain.exception.SoldOutException;

public class TicketPool {
    private int available;

    public TicketPool(int available) {
        this.available = available;
    }

    public void reserve(int quantity) {
        if (quantity <= 0) {
            throw new InvalidOrderException(("Quantity must be positive"));
        }
        if (quantity > available) {
            throw new SoldOutException("Not enough tickets available");
        }
        available -= quantity;
    }

    public int getAvailable() {
        return this.available;
    }
}
