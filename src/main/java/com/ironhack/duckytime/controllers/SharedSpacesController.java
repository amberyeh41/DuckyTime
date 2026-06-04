package com.ironhack.duckytime.controllers;

import com.ironhack.duckytime.dto.CreateSharedSpaceRequest;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.services.SharedSpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shared_spaces")
@RequiredArgsConstructor
@Slf4j
public class SharedSpacesController {
    private SharedSpaceService service;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSharedSpace(Authentication authentication, @RequestBody CreateSharedSpaceRequest createSharedSpaceRequest) {
        authentication.getName();
        log.info("Hello " + authentication.getName());
        //SharedSpace space = new SharedSpace(createSharedSpaceRequest.getName());
        //space.setAdmin();
        //service.saveSharedSpace(space);
    }
}
