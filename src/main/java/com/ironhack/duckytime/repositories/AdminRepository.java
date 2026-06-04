package com.ironhack.duckytime.repositories;

import com.ironhack.duckytime.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
