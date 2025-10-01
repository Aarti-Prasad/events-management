package com.aarti.eventsmanagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventDayDTO {
    private String eventId;
@NotNull(message = "Date is required")
    private LocalDate date;
    @NotNull(message = "Start Date is required")
    private LocalTime startTime;
    @NotNull(message = "End Date is required")
    private LocalTime endTime;
}
