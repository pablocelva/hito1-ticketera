package com.ticketera.domain;

public class OrderService {
    private final EventRepository repository;
    private final NotificationService notifier;

    public OrderService(EventRepository repository, NotificationService notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public void processOrder(String eventId, int quantity) {
        if (eventId == null || eventId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid event ID");
        }
        Event event = repository.findById(eventId);
        if (event == null) {
            throw new IllegalArgumentException("Event not found");
        }
        notifier.send("admin@ticketera.com", 
        "Order processed for: " + event.getName() + " (" + quantity + " tickets), with ID: " + eventId);
    }
}
