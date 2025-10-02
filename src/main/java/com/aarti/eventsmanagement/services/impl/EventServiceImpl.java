package com.aarti.eventsmanagement.services.impl;

import com.aarti.eventsmanagement.dtos.PaginationDTO;
import com.aarti.eventsmanagement.dtos.requests.CreateEventRequest;
import com.aarti.eventsmanagement.dtos.requests.CreateInviteeBulkRequest;
import com.aarti.eventsmanagement.dtos.requests.CreateInviteeRequest;
import com.aarti.eventsmanagement.dtos.response.*;
import com.aarti.eventsmanagement.repositories.EventRepository;
import com.aarti.eventsmanagement.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;


    @Override
    public CreateEventResponse createEvent(CreateEventRequest requestDTO) {

        //  validation

       /* boolean isEventNameDuplicate = true;
                // repository.checkIfEventnameAlreadyExists(requestDTO);

        if (isEventNameDuplicate) {
            throw new DuplicateEventNameException(requestDTO.getName());
        }*/


        String eventId = UUID.randomUUID().toString();
         int organizerId = repository.saveorganizer(requestDTO.getOrganizer(),eventId);
         repository.saveEvent(requestDTO,organizerId,eventId);
        repository.saveEventDays(eventId,requestDTO.getEventDays());

        log.info("eventId :: " + eventId);
        log.info("requestDTO.getName() :: " + requestDTO.getName());



        return new CreateEventResponse(eventId,"DRAFT",requestDTO.getName());
    }

    @Override
    public GetEventResponse getEvents(String search, int offset, int limit) {
        List<EventSummaryDTO> events=repository.getEvents(search,offset,limit);
        long total=repository.countEvents(search);
        PaginationDTO paginationDTO=new PaginationDTO(offset,limit,total);
        return new GetEventResponse(events,paginationDTO);










    }

    @Override
    public EventDetailsResponse getEventById(String id) {
        EventDetailsResponse event =repository.getEventByEventId(id);
        event.setOrganizerDTO(repository.getOrganizerByEventId(id));
        event.setEventDayDTOS(repository.getEventDayByEventId(id));
        return event;
    }

    @Override
    public void deleteByEventById(String id) {
        repository.deleteByEventId(id);

    }

    @Override
    public void updateByEventById(String id, CreateEventRequest request) {
      repository.updateByEventId(id,request);
    }

    @Override
    public int createInvitee(CreateInviteeRequest createInviteeRequest, String eventId) {

        int eventInviteeId=repository.createEventInvitee(createInviteeRequest,eventId);
        //CreateInviteeResponse createInviteeResponse=
return(eventInviteeId);

    }

    @Override
    public int[] addinviteeBulk(List<CreateInviteeBulkRequest> createInviteeBulRequest, String eventId) {
        return repository.addInviteeBulk(createInviteeBulRequest,eventId);
    }


}
