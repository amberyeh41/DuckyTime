package com.ironhack.duckytime;

import com.ironhack.duckytime.models.Admin;
import com.ironhack.duckytime.repositories.AdminRepository;
import com.ironhack.duckytime.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DuckyTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuckyTimeApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            UserService userService
    ){
        return  args -> {
            Admin admin = userService.getUser("alex");
            if (admin == null) {
                userService.saveUser(new Admin("alex", "alex123"));
            }
        };
    }

}
