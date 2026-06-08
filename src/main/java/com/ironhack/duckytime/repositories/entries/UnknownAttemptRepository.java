package com.ironhack.duckytime.repositories.entries;

import com.ironhack.duckytime.models.entries.UnknownAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnknownAttemptRepository extends JpaRepository<UnknownAttempt, Integer> {
}