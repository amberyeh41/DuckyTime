package com.ironhack.duckytime.controllers;

import com.ironhack.duckytime.dto.IncidentRequest;
import com.ironhack.duckytime.exceptions.ResourceNotFoundException;
import com.ironhack.duckytime.models.*;
import com.ironhack.duckytime.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class IncidentsController {
    private final SharedSpaceService sharedSpaceService;
    private final BookingService bookingService;
    private final IncidentService incidentService;
    private final HouseholdService householdService;

    @PostMapping("/api/shared_spaces/{sharedSpaceId}/bookings/{bookingId}/incidents")
    public Incident attemptEntry(Authentication authentication, @RequestBody IncidentRequest request, @PathVariable Long sharedSpaceId, @PathVariable Long bookingId) {
        Household household = householdService.getHouseholdByUsername(authentication.getName());
        SharedSpace space = sharedSpaceService.getSharedSpace(household.getAdmin().getId(), sharedSpaceId);
        Booking booking = bookingService.findBooking(space, household, bookingId);
        if (booking == null) {
            throw new ResourceNotFoundException("Booking not found");
        }

        return incidentService.createIncident(household, booking, request.getNotes());
    }
}
