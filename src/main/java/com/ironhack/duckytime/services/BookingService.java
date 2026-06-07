package com.ironhack.duckytime.services;

import com.ironhack.duckytime.dto.BookingRequest;
import com.ironhack.duckytime.exceptions.BookingException;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;

    public Booking createBooking (SharedSpace space, Household household, BookingRequest request){
      request.validateStartAndEndTimes();

      int maximumOccupancy = 0;
      for(LocalDateTime hourStart : request.allHourStarts()) {
         List<Booking> bookings = bookingRepository.findByOverlap(space.getId(), hourStart, hourStart.plusHours(1));
         int occupancy = 0;
         for(Booking booking : bookings){
             if (booking.getHousehold().getId().equals(household.getId())) {
                 throw new BookingException("You already have a booking within that time slot");
             }
             occupancy += booking.getAdultHeadCount() + booking.getKidHeadCount();
         }
         if(occupancy > maximumOccupancy){
             maximumOccupancy = occupancy;
         }

      }

      if (maximumOccupancy + request.getAdultHeadCount() + request.getKidHeadCount() > space.getCapacity()) {
          throw new BookingException("Over maximum capacity. Try a different time slot");
      }

      Booking booking = bookingRepository.save(new Booking(
              request.getStartTime(),
              request.getEndTime(),
              request.getAdultHeadCount(),
              request.getKidHeadCount(),
              household,
              space
      ));

      return booking;
    }


}
