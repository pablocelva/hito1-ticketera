package com.ticketera.domain;

public interface EventRepository {
    Event findById(String eventId);
}