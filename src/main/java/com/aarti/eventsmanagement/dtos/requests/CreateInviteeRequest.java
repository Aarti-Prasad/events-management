package com.aarti.eventsmanagement.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateInviteeRequest {

    private int id;
    private String name;
    private String email;
    private String phone;
    private boolean saveToMasterList;
    private String masterInviteeId;

}


//{
//        "id": 0,
//        "name": "Alice Johnson",
//        "email": "alice.johnson@example.com",
//        "phone": "9876543210",
//        "saveToMasterList": true,
//        "masterInviteeId": "123"
//}
