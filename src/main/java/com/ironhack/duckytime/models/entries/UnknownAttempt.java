package com.ironhack.duckytime.models.entries;

import com.ironhack.duckytime.models.SharedSpace;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table( name= "unknown_attempts")
@PrimaryKeyJoinColumn(name = "id")
public class UnknownAttempt extends EntryAttempt {
    public UnknownAttempt(LocalDateTime attemptTime, String padlockPin, SharedSpace sharedSpace) {
        super(attemptTime, padlockPin, sharedSpace);
    }
}
