package com.ticketera.domain;

public interface NotificationService {
    void send(String destination, String message);
}