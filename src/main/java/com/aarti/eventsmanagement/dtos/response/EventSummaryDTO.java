package com.aarti.eventsmanagement.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventSummaryDTO {
    private Long id;
    private String name;
    private String status;
}
