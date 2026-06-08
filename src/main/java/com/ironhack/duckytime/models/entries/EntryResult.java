package com.ironhack.duckytime.models.entries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntryResult {
    public enum Result {
        AUTHORIZED,
        DENIED;
    }
    private Result result;
    private String message;
}
