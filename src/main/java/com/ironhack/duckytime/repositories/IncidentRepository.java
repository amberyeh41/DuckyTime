package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {
}