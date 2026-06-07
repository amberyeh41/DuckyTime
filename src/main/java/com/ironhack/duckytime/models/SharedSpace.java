package com.ironhack.duckytime.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table( name= "shared_spaces" )
public class SharedSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Admin admin;

    protected SharedSpace() {
    }

    public SharedSpace(String name, Integer capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
