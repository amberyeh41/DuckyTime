package com.ironhack.duckytime.repositories.entries;

import com.ironhack.duckytime.models.entries.FreeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeEntryRepository extends JpaRepository<FreeEntry, Integer> {
}