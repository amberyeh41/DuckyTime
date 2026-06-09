package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {
    @Query("SELECT i FROM Incident i INNER JOIN Household h ON i.household = h WHERE h.admin = :admin")
    List<Incident> findAllForAdmin(@Param("admin") Admin admin);
}