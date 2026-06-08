package com.ironhack.duckytime.controllers;

import com.ironhack.duckytime.models.AvailableSlot;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.services.AvailabilityService;
import com.ironhack.duckytime.services.HouseholdService;
import com.ironhack.duckytime.services.SharedSpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AvailableSlotsController {
    private final AvailabilityService availabilityService;
    private final SharedSpaceService sharedSpaceService;
    private final HouseholdService householdService;

    private Household getHousehold(Authentication authentication) {
        return householdService.getHouseholdByUsername(authentication.getName());
    }

    @GetMapping("/api/shared_spaces/{sharedSpaceId}/available_slots")
    public List<AvailableSlot> listAvailableSlots(Authentication authentication, @PathVariable Long sharedSpaceId, @RequestParam Integer adultHeadcount, @RequestParam Integer kidHeadcount) {
        Household household = getHousehold(authentication);
        SharedSpace space = sharedSpaceService.getSharedSpace(household.getAdmin().getId(), sharedSpaceId);
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(7);
        return availabilityService.listAvailableSlots(space, fromDate, toDate, adultHeadcount, kidHeadcount);
    }
}
