package com.ironhack.duckytime.models.entries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table( name= "free_entries")
@PrimaryKeyJoinColumn(name = "id")
public class FreeEntry extends EntryAttempt {
    @ManyToOne
    @JoinColumn(name = "household_id")
    @JsonIgnore
    private Household household;

    public FreeEntry(LocalDateTime attemptTime, String padlockPin, SharedSpace sharedSpace, Household household) {
        super(attemptTime, padlockPin, sharedSpace);
        this.household = household;
    }

    public FreeEntry() {
    }
}
