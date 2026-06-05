package com.ironhack.duckytime;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.services.AdminService;
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

    @Bean
    CommandLineRunner run(
            AdminService adminService
    ){
        return  args -> {
            Admin admin = adminService.getUser("alex");
            if (admin == null) {
                adminService.saveUser(new Admin("alex", "alex123"));
            }
        };
    }

}
