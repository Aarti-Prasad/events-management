package com.aarti.eventsmanagement.services;

import com.aarti.eventsmanagement.dtos.requests.CreateInviteeBulkRequest;
import jakarta.validation.Valid;

public interface InviteeService {
    int[][] addBulkInvitee(@Valid CreateInviteeBulkRequest createInviteeBulkRequest);
}
