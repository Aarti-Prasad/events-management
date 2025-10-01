package com.aarti.eventsmanagement.repositories;

import com.aarti.eventsmanagement.dtos.EventDayDTO;
import com.aarti.eventsmanagement.dtos.OrganizerDTO;
import com.aarti.eventsmanagement.dtos.requests.CreateEventRequest;
import com.aarti.eventsmanagement.dtos.requests.CreateInviteeRequest;
import com.aarti.eventsmanagement.dtos.response.EventDetailsResponse;
import com.aarti.eventsmanagement.dtos.response.EventSummaryDTO;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EventRepository {
   private final JdbcTemplate jdbcTemplate;

   public int saveorganizer(OrganizerDTO organizerDTO, String eventId){

       String sql =  "INSERT INTO organiser_tbl (org_name,org_email,contact_no,event_id,crt_dt,crt_post) VALUES (?, ?, ?,?,NOW(),?)";

        KeyHolder  keyholder=new GeneratedKeyHolder();
log.info("Organizer entry error");
       jdbcTemplate.update(connection ->{
           PreparedStatement ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
           ps.setString(1,organizerDTO.getName());
           ps.setString(2,organizerDTO.getEmail());
           ps.setString(3,organizerDTO.getContactNo());
           ps.setString(4,eventId);
           ps.setString(5,"Admin");
return ps;


       },keyholder);

       return Objects.requireNonNull(keyholder.getKey()).intValue();

   }


   public void saveEvent(CreateEventRequest requestDTO, int organizerId, String eventId) {
       String sql =  "INSERT INTO event_tbl (event_name,event_venue,event_start_date,event_end_date" +
               ",no_of_days,rsvp_deadline,event_description,event_mode,event_status,organization_id,event_id,crt_dt,crt_post)" +
               "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?,?,?,NOW(),?)";
log.info("event entry error");
       KeyHolder  keyholder=new GeneratedKeyHolder();
       jdbcTemplate.update(connection ->{
           PreparedStatement ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
           ps.setString(1,requestDTO.getName());
           ps.setString(2,requestDTO.getVenue());
           ps.setDate(3, Date.valueOf(requestDTO.getStartDate()));
           ps.setDate(4, Date.valueOf(requestDTO.getEndDate()));
           ps.setInt(5, requestDTO.getNoOfEventDays());
           ps.setDate(6, Date.valueOf(requestDTO.getRsvpDeadline()));
           ps.setString(7,requestDTO.getDescription());
           ps.setString(8,requestDTO.getEventMode());
           ps.setString(9, "DRAFT");
          ps.setInt(10, organizerId);
           ps.setString(11, eventId);
           ps.setString(12,"Admin");
                    return ps;


       },keyholder);

       Objects.requireNonNull(keyholder.getKey()).intValue();
   }

   public void saveEventDays(String eventId, List<EventDayDTO> eventDays) {

       String sql= " Insert into event_day_tbl (event_id,event_date,start_time,end_time,crt_dt,crt_post) values (?, ?, ?,?,NOW(),?)";
       log.info("Event day entry");
       List<Object[]> batchArgs = eventDays.stream()
               .map(day -> new Object[]{
                       eventId,
                       Date.valueOf(day.getDate()),
                       Time.valueOf(day.getStartTime()),
                       Time.valueOf(day.getEndTime()),"Admin"

               })
               .toList();

       jdbcTemplate.batchUpdate(
               sql, batchArgs);










   }

    public List<EventSummaryDTO> getEvents(String search, int offset, int limit) {


        String sql = """
                SELECT id, event_name,
                    CASE
                        WHEN event_start_date > CURRENT_DATE THEN 'UPCOMING'
                        WHEN event_end_date < CURRENT_DATE THEN 'COMPLETED'
                        ELSE 'ONGOING'
                    END AS status
                    FROM event_tbl
                    WHERE LOWER(event_name) LIKE LOWER(?)
                    ORDER BY event_start_date
                      LIMIT ? OFFSET ?
                """;
        return jdbcTemplate.query(sql, new Object[]{"%" + search + "%",
                        limit, offset},
                (rs, rownum) -> new EventSummaryDTO(
                        rs.getLong("id"),
                        rs.getString("event_name"),
                        rs.getString("status")));
    }
        public long countEvents (String search)
        {
            String sql= """
                    
                    Select count(*) from  event_tbl where lower(event_name) like lower(?)
                    """;
return jdbcTemplate.queryForObject(sql,Long.class,"%" + search +"%");
        }

    public EventDetailsResponse getEventByEventId(String id) {

       log.info("eventdtls");
        String sql = """
        SELECT id, event_name, event_venue, event_start_date, event_end_date,
               CASE
                   WHEN event_start_date > CURRENT_DATE THEN 'UPCOMING'
                   WHEN event_end_date < CURRENT_DATE THEN 'COMPLETED'
                   ELSE 'ONGOING'
               END AS event_status
        FROM EVENT_TBL
        WHERE event_id = ?
    """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            EventDetailsResponse dto = new EventDetailsResponse();
            dto.setId(rs.getLong("id"));
            dto.setName(rs.getString("event_name"));
            dto.setVenue(rs.getString("event_venue"));
            dto.setStartDate(rs.getDate("event_start_date").toLocalDate());
            dto.setEndDate(rs.getDate("event_end_date").toLocalDate());
            dto.setStatus(rs.getString("event_status"));
            return dto;
        }, id); // 👈 ID as vararg parameter
    }

    public OrganizerDTO getOrganizerByEventId(String id) {
     String sql= """
             Select  org_name,org_email,contact_no from organiser_tbl where event_id= ?
             """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            OrganizerDTO dto = new OrganizerDTO();

            dto.setName(rs.getString("org_name"));
            dto.setEmail(rs.getString("org_email"));

            dto.setContactNo(rs.getString("contact_no"));
            return dto;
        }, id); // 👈 ID as vararg parameter




    }

    public List<EventDayDTO> getEventDayByEventId(String id) {
        String sql = """
                Select event_date,start_time,end_time from event_day_tbl where event_id= ?
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EventDayDTO eventDayDTO = new EventDayDTO();

            eventDayDTO.setDate(rs.getDate("event_date").toLocalDate());
            eventDayDTO.setStartTime(rs.getTime("start_time").toLocalTime());
            eventDayDTO.setEndTime(rs.getTime("end_time").toLocalTime());

            return eventDayDTO;
        }, id);

    }

    public void deleteByEventId(String id) {

String sql= """
        Delete from event_tbl where event_id= ?
        """ ;

        jdbcTemplate.update(sql,id);




   }

    public void updateByEventId(String id, CreateEventRequest request) {

       String sql= """
               Update event_tbl set event_name= ? ,event_venue= ? ,event_mode =?,lst_updt_dt=NOW(),lst_updt_post=? where event_id= ?
               """;
log.info(sql);
       jdbcTemplate.update(sql,request.getName(),request.getVenue(),request.getEventMode(),"ADMIN",id);
    }

    public int createEventInvitee(CreateInviteeRequest createInviteeRequest, String eventId) {

        String sql = """
               Insert into event_invitee_tbl (event_id,name,email,phone,crt_dt,crt_post)values (?,?,?,?,NOW(),?)
               """;


        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->{
            PreparedStatement ps=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,eventId);
            ps.setString(2,createInviteeRequest.getInviteeName());
            ps.setString(3, createInviteeRequest.getInviteeEmail());
            ps.setString(4, createInviteeRequest.getContactNum());
ps.setString(5,"Admin");

            return ps;
        },keyHolder);

        Number n= keyHolder.getKey();
        if(n==null)
        {
            throw new DataAccessException("Failed to retrieve") {
            };


        }
        return n.intValue();
    }
}