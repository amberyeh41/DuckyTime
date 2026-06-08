package com.ironhack.duckytime.dto;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttemptEntryRequest {
    @Digits(integer = 4, fraction = 0)
    private String padlockPin;
}
