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
    public static int EARLIEST_HOUR = 6;
    public static int LATEST_HOUR = 21;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shared_space_id")
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
