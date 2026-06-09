package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {
    List<Incident> findAllByHouseholdAdmin(Admin admin);
}