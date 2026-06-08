package com.ironhack.duckytime.models.entries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.duckytime.models.Booking;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.SharedSpace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table( name= "booked_entries")
@PrimaryKeyJoinColumn(name = "id")
public class BookedEntry extends EntryAttempt {
    @ManyToOne
    @JoinColumn(name = "household_id")
    @JsonIgnore
    private Household household;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private Booking booking;

    public BookedEntry(LocalDateTime attemptTime, String padlockPin, SharedSpace sharedSpace, Household household, Booking booking) {
        super(attemptTime, padlockPin, sharedSpace);
        this.household = household;
        this.booking = booking;
    }

    public BookedEntry() {

    }
}
