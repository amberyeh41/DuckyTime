package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    List<Household> findAllByAdminId(Long adminId);
    Household findByAdminIdAndId(Long adminId, Long id);
    Household findByBuildingNameAndFloorNumberAndDoorNumber(String buildingName, Integer floorNumber, Integer doorNumber);
}