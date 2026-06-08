package com.ironhack.duckytime.controllers;

import com.ironhack.duckytime.dto.AttemptEntryRequest;
import com.ironhack.duckytime.exceptions.EntryException;
import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.models.entries.EntryResult;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.EntryService;
import com.ironhack.duckytime.services.SharedSpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EntriesController {
    private final SharedSpaceService sharedSpaceService;
    private final EntryService entryService;
    private final AdminService adminService;

    private Admin getAdmin(Authentication authentication) {
        return adminService.getUser(authentication.getName());
    }

    @PostMapping("/api/shared_spaces/{id}/entries")
    public ResponseEntity<EntryResult> attemptEntry(Authentication authentication, @RequestBody AttemptEntryRequest request, @PathVariable Long id) {
        Long adminId = getAdmin(authentication).getId();
        SharedSpace space = sharedSpaceService.getSharedSpace(adminId, id);
        try {
            entryService.attemptEntry(space, adminId, request.getPadlockPin());
        } catch (EntryException e) {
            return new ResponseEntity<>(new EntryResult(EntryResult.Result.DENIED, e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new EntryResult(EntryResult.Result.AUTHORIZED, "Welcome!"), HttpStatus.OK);
    }
}
