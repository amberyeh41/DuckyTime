package com.ironhack.duckytime.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdUsername {
    private String buildingName;
    private int floorNumber;
    private int doorNumber;

    public HouseholdUsername(String username) {
        String[] parts = username.splitWithDelimiters("_", 3);
        if (parts.length == 5) {
            try {
                this.buildingName = parts[0];
                this.floorNumber = Integer.parseInt(parts[2]);
                this.doorNumber = Integer.parseInt(parts[4]);
            } catch (NumberFormatException e) {
                throw new UsernameNotFoundException("User not found in the database");
            }
        } else {
            throw new UsernameNotFoundException("User not found in the database");
        }
    }
}