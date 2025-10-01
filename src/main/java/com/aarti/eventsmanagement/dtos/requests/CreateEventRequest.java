package com.aarti.eventsmanagement.dtos.requests;


import com.aarti.eventsmanagement.dtos.EventDayDTO;
import com.aarti.eventsmanagement.dtos.OrganizerDTO;
import com.aarti.eventsmanagement.dtos.PaginationDTO;

import com.aarti.eventsmanagement.dtos.response.EventSummaryDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data

public class CreateEventRequest {
    private String eventId;

@NotBlank(message = "Event name is required")
@Size(max = 50,message = "Event name must not exceed 50 character")
    private String name;
    @NotBlank(message = "Venue is required")
    @Size(max = 50,message = "Venue must not exceed 150 character")
    private String venue;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    @Min(value = 1, message = "Event must be at least 1 day long")
    private int noOfEventDays;
    @NotEmpty(message = "At least one event day is required")
    @Valid
    private List<@Valid EventDayDTO> eventDays;
    @NotNull(message = "Organizer details are required")
    @Valid
    private OrganizerDTO organizer;
    @NotNull(message = "RSVP deadline is required")
    private LocalDate rsvpDeadline;
    @NotBlank(message = "Event Mode is required")
    private String eventMode;
    @NotBlank(message = "Description is required")
    @Size(max = 500,message = "Description  must not exceed 500 character")
    private String description;
    private List<EventSummaryDTO> events;
    private PaginationDTO pagination;
}
