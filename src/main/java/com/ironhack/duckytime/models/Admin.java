package com.ironhack.duckytime.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

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

    @OneToMany(mappedBy = "admin", fetch = LAZY)
    private Set<SharedSpace> shared_spaces;

    protected Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
