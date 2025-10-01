package com.aarti.eventsmanagement.services;

import com.aarti.eventsmanagement.dtos.requests.CreateEventRequest;
import com.aarti.eventsmanagement.dtos.requests.CreateInviteeRequest;
import com.aarti.eventsmanagement.dtos.response.CreateEventResponse;
import com.aarti.eventsmanagement.dtos.response.EventDetailsResponse;
import com.aarti.eventsmanagement.dtos.response.GetEventResponse;
import jakarta.validation.Valid;

public interface EventService {

    CreateEventResponse createEvent(CreateEventRequest requestDTO);

    GetEventResponse getEvents(String search, int offset, int limit);

    EventDetailsResponse getEventById(String id);

    void deleteByEventById(String id);

    void updateByEventById(String id, CreateEventRequest request);

    int createInvitee(@Valid CreateInviteeRequest createInviteeRequest, String eventId);
}
