package com.aarti.eventsmanagement.dtos.response;

import com.aarti.eventsmanagement.dtos.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetEventResponse {
    private List<EventSummaryDTO> events;
    private PaginationDTO pagination;
}
