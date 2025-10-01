package com.aarti.eventsmanagement.exceptions;

public class DuplicateEventNameException extends RuntimeException {

    public DuplicateEventNameException(String eventName) {
        super("Event name already exists: " + eventName);
    }

    public DuplicateEventNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
