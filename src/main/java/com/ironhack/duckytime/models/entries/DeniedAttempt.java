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
@Table( name= "denied_attempts")
@PrimaryKeyJoinColumn(name = "id")
public class DeniedAttempt extends EntryAttempt {
    @ManyToOne
    @JoinColumn(name = "household_id")
    @JsonIgnore
    private Household household;

    public DeniedAttempt(LocalDateTime attemptTime, String padlockPin, SharedSpace sharedSpace, Household household) {
        super(attemptTime, padlockPin, sharedSpace);
        this.household = household;
    }

    public DeniedAttempt() {
    }
}
