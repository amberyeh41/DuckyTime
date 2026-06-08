package com.ironhack.duckytime.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table( name= "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer adultHeadcount;
    private Integer kidHeadcount;

    @CreationTimestamp
    private LocalDateTime createdOn;
    private LocalDateTime cancelledOn;

    @ManyToOne
    @JoinColumn(name = "household_id")
    @JsonIgnore
    private Household household;

    @ManyToOne
    @JoinColumn(name = "shared_space_id")
    @JsonIgnore
    private SharedSpace sharedSpace;


    public Booking() {
    }

    public Booking(LocalDateTime startTime, LocalDateTime endTime, Integer adultHeadcount, Integer kidHeadcount, Household household, SharedSpace sharedSpace) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.adultHeadcount = adultHeadcount;
        this.kidHeadcount = kidHeadcount;
        this.household = household;
        this.sharedSpace = sharedSpace;
    }
}
