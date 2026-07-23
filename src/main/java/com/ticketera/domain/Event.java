package com.ticketera.domain;

public class Event {
    private String name;
    private String venue;
    private int capacity;
    private int ticketsSold;

    public Event(String name, String venue, int capacity, int ticketIsSold) {
        this.name = name;
        this.venue = venue;
        this.capacity = capacity;
        this.ticketsSold = ticketIsSold;
    }

    public String getName() {
        return this.name;
    }

    public String getVenue() {
        return this.venue;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getTicketSold() {
        return this.ticketsSold;
    }

    public boolean hasAvailability() {
        return this.ticketsSold < this.capacity;
    }

    public int getAvailableTickets() {
        return this.capacity - this.ticketsSold;
    }
}
