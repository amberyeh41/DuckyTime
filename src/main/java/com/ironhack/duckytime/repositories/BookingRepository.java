package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBySharedSpaceIdAndId(Long sharedSpaceId, Long id);
    List<Booking> findAllBySharedSpaceAndHousehold(SharedSpace space, Household household);
}


