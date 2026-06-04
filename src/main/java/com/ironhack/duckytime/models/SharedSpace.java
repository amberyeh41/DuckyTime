package com.ironhack.duckytime.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name= "shared_spaces" )
public class SharedSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected SharedSpace() {
    }

    public SharedSpace(String name) {
        this.name = name;
    }
}
