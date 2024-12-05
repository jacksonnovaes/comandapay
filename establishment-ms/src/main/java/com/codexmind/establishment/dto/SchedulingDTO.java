package com.codexmind.establishment.dto;

import java.time.LocalDateTime;
import java.util.List;


public record SchedulingDTO
        (
                Integer customerId,
                Integer employeeId,
                String description,
                LocalDateTime schedulingDate,
                List<Integer> servicesId
        ) {

}
