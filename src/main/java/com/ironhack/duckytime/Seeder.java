package com.ironhack.duckytime;

import com.ironhack.duckytime.dto.BookingRequest;
import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.BookingService;
import com.ironhack.duckytime.services.HouseholdService;
import com.ironhack.duckytime.services.SharedSpaceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class Seeder implements ApplicationRunner {
    private final AdminService adminService;
    private final SharedSpaceService sharedSpaceService;
    private final HouseholdService householdService;
    private final BookingService bookingService;

    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("seed") != null){
            seed();
            log.info("Success run seeder");
        }else{
            log.info("Seeder skipped");
        }
    }

    private void seed(){
        Admin admin = adminService.saveUser(new Admin("alex", "alex123"));
        SharedSpace space = new SharedSpace(
                "Community Pool",
                10
        );
        space.setAdmin(admin);
        space = sharedSpaceService.saveSharedSpace(space);
        Household otherHousehold = householdService.saveHousehold(new Household(
                "Gamba25",
                4,
                9,
                "gamba259",
                "1233",
                admin
        ));
        Household household = householdService.saveHousehold(new Household(
                "Gamba25",
                4,
                10,
                "gamba25",
                "1234",
                admin
        ));
        bookingService.createBooking(
                space,
                otherHousehold,
                new BookingRequest(
                    LocalDateTime.parse("2026-06-13T09:00:00"),
                        LocalDateTime.parse("2026-06-13T11:00:00"),
        6,
        4
                )
        );
        bookingService.createBooking(
                space,
                otherHousehold,
                new BookingRequest(
                        LocalDateTime.parse("2026-06-13T15:00:00"),
                        LocalDateTime.parse("2026-06-13T18:00:00"),
                        6,
                        4
                )
        );
    }
}