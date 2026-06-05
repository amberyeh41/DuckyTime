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
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            log.error("Admin not found in the database");
            throw new UsernameNotFoundException("Admin not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(admin.getRole());
            return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), authorities);
        }
    }

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
