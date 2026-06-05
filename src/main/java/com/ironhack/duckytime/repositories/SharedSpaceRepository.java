package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.SharedSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharedSpaceRepository extends JpaRepository<SharedSpace, Integer> {
    SharedSpace findByName(String name);
    List<SharedSpace> findAllByAdminId(Long adminId);
    SharedSpace findByAdminIdAndId(Long adminId, Long id);
}
