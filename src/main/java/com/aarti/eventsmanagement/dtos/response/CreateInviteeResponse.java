package com.aarti.eventsmanagement.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateInviteeResponse {
    private Integer id;
    private Integer eventId;
    private String name;
    private String email;
}
