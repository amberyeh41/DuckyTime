package com.ironhack.duckytime.controllers.admin;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.Incident;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.BookingService;
import com.ironhack.duckytime.services.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminBookingsController {
    private final BookingService bookingService;
    private final AdminService adminService;

    private Admin getAdmin(Authentication authentication) {
        return adminService.getUser(authentication.getName());
    }

    @GetMapping("/api/bookings")
    public List<Booking> listBookings(Authentication authentication) {
        return bookingService.allBookingsForAdmin(getAdmin(authentication));
    }
}
