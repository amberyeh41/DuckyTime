package com.ironhack.duckytime.controllers;

import com.ironhack.duckytime.dto.CreateSharedSpaceRequest;
import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.services.AdminService;
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
public class SharedSpacesController {
    private final SharedSpaceService sharedSpaceService;
    private final AdminService adminService;

    @PostMapping("/api/shared_spaces")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSharedSpace(Authentication authentication, @RequestBody CreateSharedSpaceRequest createSharedSpaceRequest) {
        Admin admin = adminService.getUser(authentication.getName());

        SharedSpace space = new SharedSpace(createSharedSpaceRequest.getName());
        space.setAdmin(admin);
        sharedSpaceService.saveSharedSpace(space);
    }

    @GetMapping("/api/shared_spaces")
    public List<SharedSpace> listSharedSpaces(Authentication authentication) {
        Admin admin = adminService.getUser(authentication.getName());
        return sharedSpaceService.getSharedSpaces(admin.getId());
    }
}
