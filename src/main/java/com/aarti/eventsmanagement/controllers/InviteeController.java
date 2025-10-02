package com.aarti.eventsmanagement.controllers;

import com.aarti.eventsmanagement.dtos.requests.CreateInviteeBulkRequest;
import com.aarti.eventsmanagement.services.InviteeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@AllArgsConstructor
public class InviteeController {

    private InviteeService inviteeService;

//    @PostMapping("/events/{eventId}/invitees/bulk"
//    public ResponseEntity<int[]> addInviteeList(@Valid @RequestBody CreateInviteeBulkRequest createInviteeBulRequest ,
//                                                  @PathVariable String eventId) {
//        int [] res = eventService.addinviteeBulk(createInviteeBulRequest,eventId);
//
//        return new ResponseEntity<>(res, HttpStatus.CREATED);
//
//    }



    @PostMapping("/invitees/bulk")
    public ResponseEntity <String> addBulkInvitee(@Valid @RequestBody CreateInviteeBulkRequest createInviteeBulkRequest)
    {
      int[][] result=  inviteeService.addBulkInvitee(createInviteeBulkRequest);
        log.info("result :{}", Arrays.deepToString(result));
      return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


}
