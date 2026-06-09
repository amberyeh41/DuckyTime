package com.ironhack.duckytime.services;

import com.ironhack.duckytime.models.AvailableSlot;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.SharedSpace;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AvailabilityService {
    @SqlResultSetMapping(
            name = "AvailableSlotMapping",
            classes = @ConstructorResult(
                    targetClass = AvailableSlot.class,
                    columns = {
                            @ColumnResult(name = "startTime", type = LocalDateTime.class),
                            @ColumnResult(name = "endTime", type = LocalDateTime.class),
                            @ColumnResult(name = "date", type = LocalDate.class),
                            @ColumnResult(name = "people", type = Integer.class)}))
    @Embeddable
    public static class SQLMappingConfigEntity {
    }

    @PersistenceContext
    EntityManager entityManager;

    public List<AvailableSlot> listAvailableSlots(
            SharedSpace space,
            LocalDate fromDate,
            LocalDate toDate,
            int adultHeadcount,
            int kidHeadcount
    ) {
        Query query = entityManager.createNativeQuery("""
    WITH RECURSIVE hour_series AS (
      SELECT cast(:fromDate AS datetime) AS slot_start
    
      UNION ALL
    
      -- Recursive: add one day until the end date
      SELECT DATE_ADD(slot_start, INTERVAL 1 HOUR)
      FROM hour_series
      WHERE slot_start < cast(:toDate as datetime)
    )
    SELECT slot_start as startTime, DATE_ADD(slot_start, INTERVAL 1 HOUR) AS endTime, cast(slot_start AS DATE) AS "date", COALESCE(SUM(b.adult_headcount + b.kid_headcount), 0) AS people FROM hour_series
    LEFT JOIN bookings b ON b.start_time < DATE_ADD(slot_start, INTERVAL 1 HOUR) AND b.end_time > slot_start AND b.shared_space_id = :sharedSpaceId
    WHERE (HOUR(slot_start) >= :morningStart AND HOUR(slot_start) <= :eveningEnd)
    GROUP BY slot_start
    HAVING people + :adultHeadcount + :kidHeadcount <= :capacity;
""", "AvailableSlotMapping");

        query.setParameter("fromDate", fromDate.toString());
        query.setParameter("toDate", toDate.plusDays(1).atStartOfDay().toString());
        query.setParameter("adultHeadcount", adultHeadcount);
        query.setParameter("kidHeadcount", kidHeadcount);
        query.setParameter("sharedSpaceId", space.getId());
        query.setParameter("capacity", space.getCapacity());
        query.setParameter("morningStart", Booking.EARLIEST_HOUR);
        query.setParameter("eveningEnd", Booking.LATEST_HOUR);

        return (List<AvailableSlot>) query.getResultList();
    }
}



