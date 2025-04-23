package com.barber.Entities.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record OperationDTO(
        String dayOfTheWeek,
        @JsonFormat(pattern = "HH:mm")
        LocalTime openingHours,
        @JsonFormat(pattern = "HH:mm")
        LocalTime closingTime
) {}
