package com.aarti.eventsmanagement.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateInviteeRequest {
    private String eventId;
    private int inviteeId;
    private String inviteeName;
    private String inviteeEmail;
    private String contactNum;

}
