package com.ironhack.duckytime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdRequest {
    private String buildingName;
    private int floorNumber;
    private int doorNumber;
    private String password;
    private String padlockPin;
}