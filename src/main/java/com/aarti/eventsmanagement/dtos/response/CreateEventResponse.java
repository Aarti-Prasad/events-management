package com.aarti.eventsmanagement.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
    public class CreateEventResponse {
        private String eventId;
        private String status;
        private String name;


}
