package com.ironhack.duckytime.services;

import com.ironhack.duckytime.models.*;
import com.ironhack.duckytime.repositories.IncidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncidentService {
    private final IncidentRepository incidentRepository;

    public Incident createIncident(Household household, Booking booking, String notes) {
        return incidentRepository.save(
                new Incident(
                        household,
                        booking,
                        notes
                )
        );
    }

    public List<Incident> allIncidentsForAdmin(Admin admin) {
        return incidentRepository.findAllByHouseholdAdmin(admin);
    }
}