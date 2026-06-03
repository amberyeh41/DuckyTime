package com.ironhack.duckytime;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.repositories.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DuckyTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuckyTimeApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            AdminRepository adminRepo
    ){
        return  args -> {
            Admin alex = adminRepo.save(
                    new Admin("alex", "alex123")
            );
            adminRepo.save(alex);
        };
    }
}
