package com.ticketera.domain;

public class BookingConfirmation {
    private final NotificationService notifier;

    public BookingConfirmation(NotificationService notifier) {
        this.notifier = notifier;
    }

    public void sendConfirmation(String customerEmail, String eventName) {
        if (customerEmail == null || customerEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email");
        }
        notifier.send(customerEmail, "Booking confirmed for: " + eventName);
    }
}