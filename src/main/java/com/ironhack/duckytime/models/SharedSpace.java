package com.ironhack.duckytime.models;

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

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    protected SharedSpace() {
    }

    public SharedSpace(String name) {
        this.name = name;
    }
}
