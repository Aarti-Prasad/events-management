package com.aarti.eventsmanagement.services.impl;

import com.aarti.eventsmanagement.dtos.requests.CreateInviteeBulkRequest;
import com.aarti.eventsmanagement.repositories.InviteeRepository;
import com.aarti.eventsmanagement.services.InviteeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InviteeServiceImpl implements InviteeService {
    private final InviteeRepository inviteeRepository;


    @Override
    public int[][] addBulkInvitee(@Valid CreateInviteeBulkRequest createInviteeBulkRequest) {
        return inviteeRepository.addBulkInvitee(createInviteeBulkRequest);

    }
}
