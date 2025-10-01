package com.aarti.eventsmanagement.controllers;

import com.aarti.eventsmanagement.dtos.requests.CreateEventRequest;
import com.aarti.eventsmanagement.dtos.response.CreateEventResponse;
import com.aarti.eventsmanagement.dtos.response.EventDetailsResponse;
import com.aarti.eventsmanagement.dtos.response.GetEventResponse;
import com.aarti.eventsmanagement.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// Validation
// Exception Handling

@Slf4j
@RestController
@AllArgsConstructor
public class EventController {

    // Dependency Injection 2 (Constructor Injection)
    private EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<CreateEventResponse> createEvent(@Valid @RequestBody CreateEventRequest createEventRequest) {
        CreateEventResponse createEventResponse = eventService.createEvent(createEventRequest);
        log.info("createEventResponse :: {}", createEventResponse);

        return ResponseEntity.ok(createEventResponse);
    }


    @GetMapping("/events")
    public ResponseEntity<GetEventResponse>getEvents(@RequestParam (defaultValue ="")String search,
                                                     @RequestParam (defaultValue ="0")int offset,
                                                     @RequestParam (defaultValue ="10")int limit)
    {


        GetEventResponse createEventResponse=eventService.getEvents(search,offset,limit);

        return ResponseEntity.ok(createEventResponse);
    }



    @GetMapping ("events/{id}")
    public ResponseEntity <EventDetailsResponse>getEventById(@PathVariable String id)
    {

        EventDetailsResponse eventResponse =eventService.getEventById(id);
        return ResponseEntity.ok(eventResponse);

    }



    @DeleteMapping  ("events/{id}")
    public ResponseEntity <EventDetailsResponse>deleteByEventById(@PathVariable String id)
    {

       eventService.deleteByEventById(id);
        return ResponseEntity.noContent().build();

    }



    @PutMapping("events/{id}")
    public ResponseEntity <?>updateByEventById(@PathVariable String id,@RequestBody CreateEventRequest request)
    {

        eventService.updateByEventById(id,request);
        return ResponseEntity.ok(Map.of("message","Event Updated Successfully "));

    }
}
