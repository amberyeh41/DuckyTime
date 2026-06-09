package com.ironhack.duckytime.controllers.admin;

import com.ironhack.duckytime.dto.SharedSpaceRequest;
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
public class AdminSharedSpacesController {
    private final SharedSpaceService sharedSpaceService;
    private final AdminService adminService;

    private Admin getAdmin(Authentication authentication) {
        return adminService.getUser(authentication.getName());
    }

    @PostMapping("/api/shared_spaces")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSharedSpace(Authentication authentication, @RequestBody SharedSpaceRequest sharedSpaceRequest) {
        SharedSpace space = new SharedSpace(sharedSpaceRequest.getName(),sharedSpaceRequest.getCapacity());
        space.setAdmin(getAdmin(authentication));
        sharedSpaceService.saveSharedSpace(space);
    }

    @GetMapping("/api/shared_spaces")
    public List<SharedSpace> listSharedSpaces(Authentication authentication) {
        return sharedSpaceService.getSharedSpaces(getAdmin(authentication).getId());
    }

    @GetMapping("/api/shared_spaces/{id}")
    public SharedSpace getSharedSpace(Authentication authentication, @PathVariable Long id) {
        return sharedSpaceService.getSharedSpace(getAdmin(authentication).getId(), id);
    }

    @PutMapping("/api/shared_spaces/{id}")
    public SharedSpace renameSharedSpace(Authentication authentication, @PathVariable Long id, @RequestBody SharedSpaceRequest request) {
        SharedSpace space = sharedSpaceService.getSharedSpace(getAdmin(authentication).getId(), id);
        return sharedSpaceService.renameSharedSpace(space, request.getName());
    }

    @DeleteMapping("/api/shared_spaces/{id}")
    public void deleteSharedSpace(Authentication authentication, @PathVariable Long id) {
        sharedSpaceService.deleteSharedSpace(getAdmin(authentication).getId(), id);
    }

}
