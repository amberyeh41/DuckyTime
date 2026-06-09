package com.ironhack.duckytime.models.entries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ironhack.duckytime.models.SharedSpace;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table( name= "entry_attempts")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
public class EntryAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime attemptTime;

    @Digits(integer = 4, fraction = 0)
    private String padlockPin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_space_id")
    private SharedSpace sharedSpace;

    public EntryAttempt(LocalDateTime attemptTime, String padlockPin, SharedSpace sharedSpace) {
        this.attemptTime = attemptTime;
        this.padlockPin = padlockPin;
        this.sharedSpace = sharedSpace;
    }

    public EntryAttempt() {
    }
}
