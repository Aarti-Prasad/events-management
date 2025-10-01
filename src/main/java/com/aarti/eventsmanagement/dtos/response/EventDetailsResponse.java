package com.aarti.eventsmanagement.dtos.response;

import com.aarti.eventsmanagement.dtos.EventDayDTO;
import com.aarti.eventsmanagement.dtos.OrganizerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventDetailsResponse {


    private Long id;
    private String name;
    private String venue;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    OrganizerDTO organizerDTO;
    List<EventDayDTO> eventDayDTOS;
}
