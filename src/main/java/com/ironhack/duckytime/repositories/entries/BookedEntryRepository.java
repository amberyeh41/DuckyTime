package com.ironhack.duckytime.repositories.entries;

import com.ironhack.duckytime.models.entries.BookedEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedEntryRepository extends JpaRepository<BookedEntry, Integer> {
}