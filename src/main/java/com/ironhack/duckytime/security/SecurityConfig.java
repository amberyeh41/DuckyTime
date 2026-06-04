package com.ironhack.duckytime.security;

import com.ironhack.duckytime.security.filters.CustomAuthenticationFilter;
import com.ironhack.duckytime.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManagerBuilder authManagerBuilder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/login/**").permitAll()// public endpoint, we could add more if we wanted to
//                        .requestMatchers("api/greet").permitAll()
//                        .requestMatchers("api/greet/personal").hasAnyAuthority("ROLE_USER")
//                        .requestMatchers(GET, "/api/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                        .requestMatchers(POST, "/api/users").hasAnyAuthority("ROLE_ADMIN")
//                        .requestMatchers(POST, "/api/roles").hasAnyAuthority("ROLE_ADMIN")
//                        .requestMatchers(POST, "/api/roles/add-to-user").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()); // any other endpoints require authentication

        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);

        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }
}