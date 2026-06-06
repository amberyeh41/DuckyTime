package com.ironhack.duckytime.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name= "households" )
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String buildingName;
    private int floorNumber;
    private int doorNumber;
    private String password;

    @Digits(integer = 4, fraction = 0)
    private String padlockPin;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Admin admin;

    public Household() {
    }

    public Household(String buildingName, int floorNumber, int doorNumber, String password, String padlockPin, Admin admin) {
        this.buildingName = buildingName;
        this.floorNumber = floorNumber;
        this.doorNumber = doorNumber;
        this.password = password;
        this.padlockPin = padlockPin;
        this.admin = admin;
    }
}
