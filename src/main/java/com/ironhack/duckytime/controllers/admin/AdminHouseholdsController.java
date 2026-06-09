package com.ironhack.duckytime.controllers.admin;

import com.ironhack.duckytime.dto.HouseholdRequest;
import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.HouseholdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminHouseholdsController {
    private final HouseholdService householdService;
    private final AdminService adminService;

    private Admin getAdmin(Authentication authentication) {
        return adminService.getUser(authentication.getName());
    }


    @PostMapping("/api/households")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHousehold(Authentication authentication, @RequestBody HouseholdRequest householdRequest) {
        Household household = new Household(
                householdRequest.getBuildingName(),
                householdRequest.getFloorNumber(),
                householdRequest.getDoorNumber(),
                householdRequest.getPassword(),
                householdRequest.getPadlockPin(),
                getAdmin(authentication)
        );

        householdService.saveHousehold(household);
    }

    @GetMapping("/api/households")
    public List<Household> listHouseholds(Authentication authentication) {
        return householdService.getHouseholds(getAdmin(authentication).getId());
    }

    @GetMapping("/api/households/{id}")
    public Household getHousehold(Authentication authentication, @PathVariable Long id) {
        return householdService.getHousehold(getAdmin(authentication).getId(), id);
    }

    @DeleteMapping("/api/households/{id}")
    public void deleteHousehold(Authentication authentication, @PathVariable Long id) {
        householdService.deleteHousehold(getAdmin(authentication).getId(), id);
    }

}
