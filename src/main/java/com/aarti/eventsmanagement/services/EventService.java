package com.aarti.eventsmanagement.services;

import com.aarti.eventsmanagement.dtos.requests.CreateEventRequest;
import com.aarti.eventsmanagement.dtos.response.CreateEventResponse;
import com.aarti.eventsmanagement.dtos.response.EventDetailsResponse;
import com.aarti.eventsmanagement.dtos.response.GetEventResponse;

public interface EventService {

    CreateEventResponse createEvent(CreateEventRequest requestDTO);

    GetEventResponse getEvents(String search, int offset, int limit);

    EventDetailsResponse getEventById(String id);

    void deleteByEventById(String id);

    void updateByEventById(String id, CreateEventRequest request);
}
