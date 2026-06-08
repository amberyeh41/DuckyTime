package com.ironhack.duckytime.dto;


import com.ironhack.duckytime.exceptions.BookingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer adultHeadcount;
    private Integer kidHeadcount;

    public void validateStartAndEndTimes() throws BookingException {
        if (startTime.isAfter(endTime)) {
            throw new BookingException("Start time needs to be before end time");
        }
        if (startTime.isBefore(LocalDateTime.now()) ){
            throw new BookingException("Start time should be in the future");
        }
        if (startTime.getMinute() != 0){
            throw new BookingException("Start time should be on the hour");
        }
        if (endTime.getMinute() != 0){
            throw new BookingException("End time should be on the hour");
        }
        if (endTime.getHour() - startTime.getHour() > 3) {
            throw new BookingException("You can only book max 3 hours");
        }
    }
    
    public List<LocalDateTime> allHourStarts() {
        List<LocalDateTime> hourStarts = new ArrayList<>();

        int hourCount = endTime.getHour() - startTime.getHour();
        for(int i = 0; i < hourCount; ++i) {
            hourStarts.add(startTime.plusHours(i));
        }

        return hourStarts;
    }
}