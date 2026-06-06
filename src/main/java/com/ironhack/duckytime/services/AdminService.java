package com.ironhack.duckytime.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Admin saveUser(Admin user) {
        log.info("Saving new user {} to the database", user.getUsername());
        // Encode the user's password for security before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return adminRepository.save(user);
    }

    public Admin getUser(String username) {
        log.info("Fetching user {}", username);
        return adminRepository.findByUsername(username);
    }

    public List<Admin> getUsers() {
        log.info("Fetching all users");
        return adminRepository.findAll();
    }
}
