package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBySharedSpaceIdAndId(Long sharedSpaceId, Long id);
    Booking findBySharedSpaceAndHouseholdAndAndId(SharedSpace space, Household household, Long id);
    List<Booking> findAllBySharedSpaceAndHousehold(SharedSpace space, Household household);

    @Query("SELECT b FROM Booking b WHERE b.cancelledOn IS NULL AND b.household = :household AND b.sharedSpace = :sharedSpace AND b.startTime <= :now AND b.endTime > :now")
    Booking findActiveBySharedSpaceAndHouseHold(@Param("sharedSpace") SharedSpace space, @Param("household") Household household, @Param("now") LocalDateTime now);

    List<Booking> findAllByHouseholdAdmin(Admin admin);
}


