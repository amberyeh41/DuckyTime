package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.SharedSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedSpaceRepository extends JpaRepository<Admin, Integer> {
    SharedSpace findByName(String name);
}
