package com.aarti.eventsmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationDTO {
    private int offset;
    private int limit;
    private long total;
}
