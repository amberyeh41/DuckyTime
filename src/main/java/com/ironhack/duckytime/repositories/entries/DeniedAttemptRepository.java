package com.ironhack.duckytime.repositories.entries;

import com.ironhack.duckytime.models.entries.DeniedAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeniedAttemptRepository extends JpaRepository<DeniedAttempt, Integer> {
}