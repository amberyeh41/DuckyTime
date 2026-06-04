package com.ironhack.duckytime.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name= "admins" )
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Role role = Role.ADMIN;

    protected Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
