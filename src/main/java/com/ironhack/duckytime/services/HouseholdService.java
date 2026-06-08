package com.ironhack.duckytime.services;


import com.ironhack.duckytime.dto.HouseholdUsername;
import com.ironhack.duckytime.exceptions.ResourceNotFoundException;
import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.repositories.HouseholdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseholdService {
    private final HouseholdRepository householdRepository;
    private final PasswordEncoder passwordEncoder;

    public Household saveHousehold(Household household) {
        household.setPassword(passwordEncoder.encode(household.getPassword()));
        return householdRepository.save(household);
    }

    public List<Household> getHouseholds(Long adminId) {
        return householdRepository.findAllByAdminId(adminId);
    }

    public Household getHousehold(Long adminId, Long id) {
        return householdRepository.findByAdminIdAndId(adminId, id);
    }
    public Household getHouseholdByUsername(String username) {
        HouseholdUsername householdUsername = new HouseholdUsername(username);
        return householdRepository.findByBuildingNameAndFloorNumberAndDoorNumber(
                householdUsername.getBuildingName(),
                householdUsername.getFloorNumber(),
                householdUsername.getDoorNumber()
        );
    }

    public Household getHousehold(Long adminId, String padlockPin) {
        return householdRepository.findByAdminIdAndPadlockPin(adminId, padlockPin);
    }

    public void deleteHousehold(Long adminId, Long id) {
        Household space = householdRepository.findByAdminIdAndId(adminId, id);
        if (space == null) {
            throw new ResourceNotFoundException("Household not found");
        }
        householdRepository.deleteById(id.intValue());
    }
}