package com.ironhack.duckytime.controllers.admin;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Incident;
import com.ironhack.duckytime.models.entries.EntryAttempt;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.EntryService;
import com.ironhack.duckytime.services.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminEntriesController {
    private final EntryService entryService;
    private final AdminService adminService;

    private Admin getAdmin(Authentication authentication) {
        return adminService.getUser(authentication.getName());
    }

    @GetMapping("/api/entries")
    public List<EntryAttempt> listEntries(Authentication authentication) {
        return entryService.allEntriesForAdmin(getAdmin(authentication));
    }
}
