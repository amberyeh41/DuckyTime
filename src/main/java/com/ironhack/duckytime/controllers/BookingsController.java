package com.ironhack.duckytime.controllers;

import com.ironhack.duckytime.dto.BookingRequest;
import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.BookingService;
import com.ironhack.duckytime.services.HouseholdService;
import com.ironhack.duckytime.services.SharedSpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookingsController {
    private final BookingService bookingService;
    private final SharedSpaceService sharedSpaceService;
    private final HouseholdService householdService;

    private Household getHousehold(Authentication authentication) {
        return householdService.getHouseholdByUsername(authentication.getName());
    }

    @PostMapping("/api/shared_spaces/{sharedSpaceId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(Authentication authentication, @PathVariable Long sharedSpaceId, @RequestBody BookingRequest bookingRequest) {
        Household household = getHousehold(authentication);
        SharedSpace space = sharedSpaceService.getSharedSpace(household.getAdmin().getId(), sharedSpaceId);
        return bookingService.createBooking(space, household, bookingRequest);
    }
}