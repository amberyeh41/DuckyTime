package com.ironhack.duckytime.services;

import com.ironhack.duckytime.dto.HouseholdUsername;
import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.models.Household;
import com.ironhack.duckytime.models.Role;
import com.ironhack.duckytime.repositories.AdminRepository;
import com.ironhack.duckytime.repositories.HouseholdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final HouseholdRepository householdRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First, let's assume the user trying to log in is an Admin
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            // If we couldn't find an Admin, let's try to find a Household.
            // The household username is buildingName_floorNumber_doorNumber
            HouseholdUsername householdUsername = new HouseholdUsername(username);
            Household household = householdRepository.findByBuildingNameAndFloorNumberAndDoorNumber(
                    householdUsername.getBuildingName(),
                    householdUsername.getFloorNumber(),
                    householdUsername.getDoorNumber()
            );
            if (household == null) {
                throw new UsernameNotFoundException("User not found in the database");
            }

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(Role.HOUSEHOLD);
            return new org.springframework.security.core.userdetails.User(username, household.getPassword(), authorities);
        } else {
            log.info("User found in the database: {}", username);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(admin.getRole());
            return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), authorities);
        }
    }
}