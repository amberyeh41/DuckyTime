package com.ironhack.duckytime.controllers.admin;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Incident;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminIncidentsController {
    private final IncidentService incidentService;
    private final AdminService adminService;

    private Admin getAdmin(Authentication authentication) {
        return adminService.getUser(authentication.getName());
    }

    @GetMapping("/api/incidents")
    public List<Incident> listIncidents(Authentication authentication) {
        return incidentService.allIncidentsForAdmin(getAdmin(authentication));
    }
}
