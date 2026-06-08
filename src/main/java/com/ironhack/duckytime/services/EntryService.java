package com.ironhack.duckytime.services;

import com.ironhack.duckytime.exceptions.EntryException;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.models.entries.BookedEntry;
import com.ironhack.duckytime.models.entries.DeniedAttempt;
import com.ironhack.duckytime.models.entries.FreeEntry;
import com.ironhack.duckytime.models.entries.UnknownAttempt;
import com.ironhack.duckytime.repositories.BookingRepository;
import com.ironhack.duckytime.repositories.entries.BookedEntryRepository;
import com.ironhack.duckytime.repositories.entries.DeniedAttemptRepository;
import com.ironhack.duckytime.repositories.entries.FreeEntryRepository;
import com.ironhack.duckytime.repositories.entries.UnknownAttemptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntryService {
    private final HouseholdService householdService;
    private final UnknownAttemptRepository unknownAttemptRepository;
    private final FreeEntryRepository freeEntryRepository;
    private final DeniedAttemptRepository deniedAttemptRepository;
    private final BookedEntryRepository bookedEntryRepository;
    private final BookingRepository bookingRepository;

    public void attemptEntry(SharedSpace space, Long adminId, String padlockPin) throws EntryException {
        LocalDateTime now = LocalDateTime.now();
        Household household = householdService.getHousehold(adminId, padlockPin);
        if (household == null) {
            unknownAttemptRepository.save(new UnknownAttempt(
                now, padlockPin, space
            ));
            throw new EntryException("Unknown PIN");
        }

        if (now.getHour() < Booking.EARLIEST_HOUR || now.getHour() > Booking.LATEST_HOUR) {
            freeEntryRepository.save(new FreeEntry(
                    now, padlockPin, space, household
            ));
            return;
        }

        Booking booking = bookingRepository.findActiveBySharedSpaceAndHouseHold(space, household, now);
        if (booking == null) {
            deniedAttemptRepository.save(new DeniedAttempt(
                    now, padlockPin, space, household
            ));
            throw new EntryException("Cannot enter without an active booking");
        }

        // A valid entry for a valid booking!
        bookedEntryRepository.save(new BookedEntry(
                now, padlockPin, space, household, booking
        ));
    }
}
