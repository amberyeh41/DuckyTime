package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.cancelledOn IS NULL AND b.sharedSpace.id = :sharedSpaceId AND b.startTime < :toTime AND b.endTime > :fromTime")
    List<Booking> findByOverlap(@Param("sharedSpaceId") Long sharedSpaceId, @Param("fromTime") LocalDateTime fromTime, @Param("toTime") LocalDateTime toTime);
}
