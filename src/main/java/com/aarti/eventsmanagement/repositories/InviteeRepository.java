package com.aarti.eventsmanagement.repositories;

import com.aarti.eventsmanagement.dtos.requests.CreateInviteeBulkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InviteeRepository {
    private final JdbcTemplate jdbcTemplate;
    public int[][] addBulkInvitee(@Valid CreateInviteeBulkRequest createInviteeBulkRequest) {
        String sql = "INSERT INTO invitee_master_tbl (name,email,phone,crt_dt,crt_post) VALUES (?,?,?,NOW(),?)";

        return jdbcTemplate.batchUpdate(
                sql,
                createInviteeBulkRequest.getInvitees(),
                1,
                (ps, invitee) -> {
                    ps.setString(1, invitee.getName());
                    ps.setString(2, invitee.getEmail());
                    ps.setString(3, invitee.getPhone());
                    ps.setString(4, "Admin");
                }
        );
    }

}
