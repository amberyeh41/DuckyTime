package com.ironhack.duckytime.repositories.entries;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.entries.EntryAttempt;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface EntryAttemptRepository extends JpaRepository<EntryAttempt, Integer> {
    List<EntryAttempt> findAllBySharedSpaceAdmin(Admin admin);
}