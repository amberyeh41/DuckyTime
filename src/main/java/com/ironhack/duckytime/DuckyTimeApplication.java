package com.ironhack.duckytime;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.services.AdminService;
import com.ironhack.duckytime.services.SharedSpaceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class DuckyTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuckyTimeApplication.class, args);
    }
    
}
