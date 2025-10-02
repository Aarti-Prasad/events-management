package com.aarti.eventsmanagement.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateInviteeBulkRequest {

    private List<CreateInviteeRequest> invitees;

}
/*
{
invitees: [     {
            "id":0,
            "name":"Alice Johnson",
            "email":"alice.johnson@example.com",
            "phone":"9876543210",
            "saveToMasterList":true,
            "masterInviteeId":"123"
            },
            {
            "id":0,
            "name":"Alice Johnson",
            "email":"alice.johnson@example.com",
            "phone":"9876543210",
            "saveToMasterList":true,
            "masterInviteeId":"123"
            },
            {
            "id":0,
            "name":"Alice Johnson",
            "email":"alice.johnson@example.com",
            "phone":"9876543210",
            "saveToMasterList":true,
            "masterInviteeId":"123"
            }
        ]
}

 */








