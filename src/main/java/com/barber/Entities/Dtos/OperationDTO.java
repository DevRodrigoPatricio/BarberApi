package com.barber.Entities.Dtos;

import java.time.LocalDateTime;

public record OperationDTO(
        String dayOfTheWeek,
        LocalDateTime openingHours,
        LocalDateTime closingTime
) {}
