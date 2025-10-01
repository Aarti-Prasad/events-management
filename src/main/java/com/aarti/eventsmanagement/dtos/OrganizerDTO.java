package com.aarti.eventsmanagement.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data

public class OrganizerDTO {
    private String eventId;
@NotBlank (message = "Organizer name is mandatory")
@Size(max = 50, message="organiser name should be 50 character")
    private String name;
@Email(message ="Email should be valid")
@NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Contact number is required")
    @Size(min = 10, max = 15, message = "Contact number must be between 10 and 15 digits")
    private String contactNo;
}
