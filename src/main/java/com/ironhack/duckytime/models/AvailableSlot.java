package com.ironhack.duckytime.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class AvailableSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate date;
    private int people;

}
