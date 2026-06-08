package com.ironhack.duckytime.services;

import com.ironhack.duckytime.dto.BookingRequest;
import com.ironhack.duckytime.exceptions.BookingException;
import com.ironhack.duckytime.exceptions.ResourceNotFoundException;
import com.ironhack.duckytime.models.*;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final AvailabilityService availabilityService;

    public Booking createBooking (SharedSpace space, Household household, BookingRequest request){
      request.validateStartAndEndTimes();

      LocalDate fromDate = request.getStartTime().toLocalDate();
      LocalDate toDate = request.getEndTime().toLocalDate();
      List<AvailableSlot> slots = availabilityService.listAvailableSlots(space, fromDate, toDate, request.getAdultHeadcount(), request.getKidHeadcount());

      log.info("Hello {}", slots);
      Set<LocalDateTime> requiredSlotStarts = new HashSet<>(request.allHourStarts());
      log.info("Required Slot Starts {}", requiredSlotStarts);
      for(AvailableSlot slot : slots) {
          requiredSlotStarts.remove(slot.getStartTime());
      }

      if (!requiredSlotStarts.isEmpty()) {
          StringBuilder joined = new StringBuilder("Unavailable times: ");
          for (LocalDateTime unavailable : requiredSlotStarts) {
              joined.append(unavailable.toLocalTime().toString()).append(",");
          }
          throw new BookingException("Over maximum capacity. Try a different time slot or booking length. " + joined);
      }

      return bookingRepository.save(new Booking(
              request.getStartTime(),
              request.getEndTime(),
              request.getAdultHeadcount(),
              request.getKidHeadcount(),
              household,
              space
      ));
    }

    public List<Booking> listBookings(SharedSpace space, Household household) {
        return bookingRepository.findAllBySharedSpaceAndHousehold(space, household);
    }

    public void cancelBooking(SharedSpace space, Long id){
        Booking booking = bookingRepository.findBySharedSpaceIdAndId(space.getId(), id);
        if (booking == null) {
            throw new ResourceNotFoundException("Booking not found");
        }
        if (booking.getCancelledOn() != null) {
            throw new BookingException("You can not cancel a booking twice");
        }
        if (booking.getStartTime().isBefore(LocalDateTime.now())) {
            throw new BookingException("You can only cancel future bookings");
        }
        booking.setCancelledOn(LocalDateTime.now());
        bookingRepository.save(booking);
    }

}
